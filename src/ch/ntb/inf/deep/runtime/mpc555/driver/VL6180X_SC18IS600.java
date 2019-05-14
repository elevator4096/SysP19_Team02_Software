package ch.ntb.inf.deep.runtime.mpc555.driver;

import ch.ntb.inf.deep.unsafe.US;
import ch.ntb.inf.deep.runtime.mpc555.IntbMpc555HB;


public class VL6180X_SC18IS600 implements IntbMpc555HB,VL6180XAddr{

	private final byte dummybyte = (byte) 0x6F;		// = 111 dec.
	
	private boolean rangeMode;
	private boolean continuousMode;
	private final byte defaultSlaveAddr = 0x29;
	
	private final byte WRITE_TO_INTERNAL_REGISTER = (byte)0x20;
	
	private byte tranOffset;

	//----------------------------------------------INITIALIZE--------------------------------------------------------------------------------------------------
	
	/**
	 * Initialisierung des SPI, SC18IS600 Konverters sowie sämtliche VL6180X Sensoren.
	 * @param sensors
	 * 			Anzahl der verwendeten Sensoren.
	 */
	public VL6180X_SC18IS600(int sensors){
		
		tranOffset = 0;
		
		do{
		initSpiShiftReg();									//initialize spi shiftregister
		initSC18IS600();									//initialize spi to i2c converter
		} while(testMiso() != (byte)0xAA);
		

		
		//for(int i = 0; i < 4; i++){
		for(int i = 0; i < 2; i++){
			
			initVL6180X(i);									//initialize each sensor
			for(int j = 0; j < 10000; j++){}
			System.out.println("Konstruktor VLX");				// delay
		}
		
	}

	/**
	 * Initialisierung des SPI Ports (CS2).
	 */
	private static void initSpiShiftReg() {

		US.PUT2(SPCR1, 0x0000); 							// disable SPI	0x0000
		US.PUT1(PQSPAR, 0x23); 								// use PCS2, MOSI, MISO in SPI Mode 0x23
		US.PUT1(DDRQS, 0x26); 								// define PCS2, SCK, MOSI as outputs 0x26
		US.PUT2(PORTQS, 0x0020); 							// 0x00 set default value to low, except PCS2 //0x20 --> Converters CS = active low
		US.PUT2(SPCR0, 0x8320); 							// Master Mode, baud rate test, 8 bits sent/transfer 0x80FF		--> x3xx = clockpolarity inactive high
		US.PUT2(SPCR2, 0x0200); 							// Disable interrupts and wraparound mode 0x00 || enable wraparound 0x40
		US.PUT1(COMDRAM, 0x8B); 							// Eight bits, PCS2 low 0x0B 	// 0x8B = keep chip select after transfer
	}
	
	/**
	 * Initialisierung des SPI to I2C Converters.
	 */
	private void initSC18IS600(){
		
		// set MSB first - default
		writeToTRANRAM((byte)0x18);							// configure spi
		writeToTRANRAM((byte)0x42);							// set MSB first
		sendTRANRAM();
		
		// set I2C address to 0x00 - default
		writeToTRANRAM(WRITE_TO_INTERNAL_REGISTER);			// write to internal register
		writeToTRANRAM((byte)0x05);							// write to register I2CAddr
		writeToTRANRAM((byte)0x00);							// set I2CAddr to 0x00
		sendTRANRAM();
		
		// set IOConfig to push-pull 0xAA // open-drain 0xFF // quasi-bidirectional 0x00
		writeToTRANRAM(WRITE_TO_INTERNAL_REGISTER);			// write to internal register
		writeToTRANRAM((byte)0x00);							// write to register IOConfig
		writeToTRANRAM((byte)0xAA);							// set PinConfigs to 0xAA = push-pull
		sendTRANRAM();
		
		// set I2CClk 
		writeToTRANRAM(WRITE_TO_INTERNAL_REGISTER);			// write to internal register
		writeToTRANRAM((byte)0x02);							// write to register IOConfig
		writeToTRANRAM((byte)19);							// set PinConfigs to 0x05 = 97kHz
		sendTRANRAM();
		
		// set IOState: all GPIO's low
		writeToTRANRAM(WRITE_TO_INTERNAL_REGISTER);			// write to internal register
		writeToTRANRAM((byte)0x01);							// write to register IOState
		writeToTRANRAM((byte)0x00);							// set PinConfigs low, 0x3F = GPIO's high
		sendTRANRAM();
	}
	
	private void initVL6180X(int sensor){
		int newAddr = sensor +1;
		byte gpio = 0;
	
		// set GPIOx SC18IS600 on high
		writeToTRANRAM(WRITE_TO_INTERNAL_REGISTER);			// write to internal register
		writeToTRANRAM((byte)0x01);							// write to register IOState
		
		/*switch(sensor){
		case 3:
			gpio +=0x02;
		case 2:
			gpio +=0x20;									// TODO check GPIO3 on new print
		case 1:
			gpio +=0x08;
		case 0:
			gpio+=0x01;
		}
		*/
		
		switch(sensor){
		case 1:
			//GPIO 1
			gpio +=0x02;
			
		case 2:
			gpio +=0x20;									// TODO check GPIO3 on new print
		//case 1:
			//gpio +=0x08;
			
		case 0:
			//GPIO 0
			gpio+=0x01;
		}
		
		writeToTRANRAM((byte)gpio);		// set GPIOx high
		sendTRANRAM();
		
		for(int i = 0; i < 250000; i++){
			// short delay ~1ms
		}
		
		// check fresh out of reset status
		byte val;
		do{
			val = readReg8(defaultSlaveAddr, SYSTEM__FRESH_OUT_OF_RESET);
		} while ((val&0xFF)!=(byte)0x01);
		
		
		// change fresh out of reset status
		writeReg8(defaultSlaveAddr, SYSTEM__FRESH_OUT_OF_RESET, 0x00);
		
		// Apply tuning settings
		sensorMinReq(defaultSlaveAddr);
		configDefault(defaultSlaveAddr);

		
		// set new slave address
		writeReg8(defaultSlaveAddr, I2C_SLAVE__DEVICE_ADDRESS, newAddr);
		
	}
	
	private void sensorMinReq(int addr){						// DM00123019-short.pdf - Appendix 1: VL6180X Tuning Settings
		writeReg8(addr, 0x0207, 0x01);
		writeReg8(addr, 0x0208, 0x01);
		writeReg8(addr, 0x0133, 0x01);
	    writeReg8(addr, 0x0096, 0x00);
	    writeReg8(addr, 0x0097, 0xFD); 								// RANGE_SCALER = 253
	    writeReg8(addr, 0x00E3, 0x00);
	    writeReg8(addr, 0x00E4, 0x04);
	    writeReg8(addr, 0x00E5, 0x02);
	    writeReg8(addr, 0x00E6, 0x01);
	    writeReg8(addr, 0x00E7, 0x03);
	    writeReg8(addr, 0x00F5, 0x02);
	    writeReg8(addr, 0x00D9, 0x05);
	    writeReg8(addr, 0x00DB, 0xCE);
	    writeReg8(addr, 0x00DC, 0x03);
	    writeReg8(addr, 0x00DD, 0xF8);
	    writeReg8(addr, 0x009F, 0x00);
	    writeReg8(addr, 0x00A3, 0x3C);
	    writeReg8(addr, 0x00B7, 0x00);
	    writeReg8(addr, 0x00BB, 0x3C);
	    writeReg8(addr, 0x00B2, 0x09);
	    writeReg8(addr, 0x00CA, 0x09);
	    writeReg8(addr, 0x0198, 0x01);
	    writeReg8(addr, 0x01B0, 0x17);
	    writeReg8(addr, 0x01AD, 0x00);
	    writeReg8(addr, 0x00FF, 0x05);
	    writeReg8(addr, 0x0100, 0x05);
	    writeReg8(addr, 0x0199, 0x05);
	    writeReg8(addr, 0x0109, 0x07);
		writeReg8(addr, READOUT__AVERAGING_SAMPLE_PERIOD, 0x30);	// 0x10A
	    writeReg8(addr, 0x003F, 0x46);
	    writeReg8(addr, 0x01A6, 0x1B);
	    writeReg8(addr, 0x01AC, 0x3E);
	    writeReg8(addr, 0x01A7, 0x1F);
	    writeReg8(addr, 0x0103, 0x01);
	    writeReg8(addr, 0x0030, 0x00);
		writeReg8(addr, SYSRANGE__INTERMEASUREMENT_PERIOD, 0x0A);	// 0x01B
	    writeReg8(addr, SYSALS__INTERMEASUREMENT_PERIOD, 0x0A); 	// 0x03E
	    writeReg8(addr, 0x0131, 0x04);
	    writeReg8(addr, SYSTEM__MODE_GPIO1, 0x10);					// 0x011
	    writeReg8(addr, SYSTEM__INTERRUPT_CONFIG_GPIO, 0x24);		// 0x014
	    writeReg8(addr, SYSRANGE__VHV_REPEAT_RATE, 0xFF);			// 0x031
	    writeReg8(addr, 0x00D2, 0x01);
	    writeReg8(addr, 0x00F2, 0x01);
	}	
	
	private void configDefault(int addr){
	    
		writeReg8(addr, SYSALS__ANALOGUE_GAIN, 0x46);
		writeReg16(addr, SYSALS__INTEGRATION_PERIOD, 0x0063);
		writeReg8(addr, SYSRANGE__VHV_RECALIBRATE, 0x01);
		writeReg8(addr, SYSRANGE__MAX_CONVERGENCE_TIME, 0x32);
		writeReg8(addr, INTERLEAVED_MODE__ENABLE, 0x00);
		writeReg8(addr, SYSRANGE__RANGE_CHECK_ENABLES, 0x01);
		writeReg16(addr, SYSRANGE__EARLY_CONVERGENCE_ESTIMATE, 0x007B);
		writeReg8(addr, FIRMWARE__RESULT_SCALER, 0x01);
		
		writeReg8(addr, SYSRANGE__MAX_AMBIENT_LEVEL_MULT, 0x0A);
	}

	//------------------------------------------!INITIALIZE--------------------------------------------------------------------------------------------------
	
	/**
	 * Distanz eines Single-Shot-Measurements.
	 * @param addr
	 * 			Slave Device Address.
	 * @return
	 * 			Distanz in mm.
	 */
	public byte getSingleRangeValue(int addr){					
		byte val;
				
		writeReg8(addr, SYSRANGE__START, 0x01);
		
		do{
			val = readReg8(addr, RESULT__INTERRUPT_STATUS_GPIO);
		}while((val&0xFF)!=0x04);
		
		byte value = readReg8(addr, RESULT__RANGE_VAL);

		writeReg8(addr, SYSTEM__INTERRUPT_CLEAR, 0x07);
		return value;
	}
	
	/**
	 * RangeResultStatus-Register des Sensors.
	 * @param addr
	 * 		Slave Device Address.
	 * @return
	 * 		Wert des Status-Registers.
	 */
	public byte getRangeResultStatus(int addr){
		return readReg8(addr, RESULT__RANGE_STATUS);		
	}
	
	
	/**
	 * Distanzmessung des VL6180X Sensors im kontinuierlichen Modus betreiben. Zum Anhalten, erneut die Startfunktion aufrufen.
	 * @param addr
	 * 			Slave Device Address.
	 */
	public void startRangeContMode(int addr){
		writeReg8(addr, SYSRANGE__START, 0x03);
	}
	
	/**
	 * Distanz des Sensors auslesen.
	 * @param addr
	 * 			Slave Device Address.
	 * @return
	 * 			Distanz in mm.
	 */
	public byte getRangeContValue(int addr){
		byte val = readReg8(addr, RESULT__RANGE_VAL);
		writeReg8(addr, SYSTEM__INTERRUPT_CLEAR, 0x07);
		return val;
	}
	
// ----------------------------------------------------- read / write to VL6180X ----------------------------------------------------------------
		
	/**
	 * Lesen eines Registers des VL6180X Sensors.
	 * @param addr
	 * 			I2C Slave Device Address des Sensors.
	 * @param reg
	 * 			Zu lesendes Register des Sensors.
	 * @return
	 * 			Wert im Register.
	 */
	private byte readReg8(int addr, int reg){
		writeToTRANRAM((byte)0x02);						// read after write
		writeToTRANRAM((byte)0x02);						// 2 bytes write
		writeToTRANRAM((byte)0x01);						// 1 byte read
		writeToTRANRAM((byte)(addr << 1));				// slave address write
		writeToTRANRAM((byte)((reg >> 8) & 0xFF));		// MSB reg
		writeToTRANRAM((byte)(reg & 0xFF));				// LSB reg
		writeToTRANRAM((byte)(addr << 1));				// slave address read
		sendTRANRAM();
		
		do{												// converters i2c-status reg should be 0xf0 if data is available
			writeToTRANRAM(0x21);
			writeToTRANRAM(0x04);
			writeToTRANRAM(dummybyte);
		}while((sendTRANRAM()&0xFF)!=0xF0);
		
		return (byte) (readBuffer(1)&0xFF);							// data is in converters buffer
	}
	
	private int readReg16(int addr, int reg){
		writeToTRANRAM((byte)0x02);						// read after write
		writeToTRANRAM((byte)0x02);						// 2 bytes write
		writeToTRANRAM((byte)0x02);						// 1 byte read
		writeToTRANRAM((byte)(addr << 1));				// slave address write
		writeToTRANRAM((byte)((reg >> 8) & 0xFF));		// MSB reg
		writeToTRANRAM((byte)(reg & 0xFF));				// LSB reg
		writeToTRANRAM((byte)(addr << 1));				// slave address read
		sendTRANRAM();
		
		do{												// converters i2c-status reg should be 0xf0 if data is available
			writeToTRANRAM(0x21);
			writeToTRANRAM(0x04);
			writeToTRANRAM(dummybyte);
		}while((sendTRANRAM()&0xFF)!=0xF0);
		
		return (readBuffer(2)&0xFF);							// data is in converters buffer
	}
	
	private int readReg32(int addr, int reg){
		writeToTRANRAM((byte)0x02);						// read after write
		writeToTRANRAM((byte)0x02);						// 2 bytes write
		writeToTRANRAM((byte)0x04);						// 1 byte read
		writeToTRANRAM((byte)(addr << 1));				// slave address write
		writeToTRANRAM((byte)((reg >> 8) & 0xFF));		// MSB reg
		writeToTRANRAM((byte)(reg & 0xFF));				// LSB reg
		writeToTRANRAM((byte)(addr << 1));				// slave address read
		sendTRANRAM();
		
		do{												// converters i2c-status reg should be 0xf0 if data is available
			writeToTRANRAM(0x21);
			writeToTRANRAM(0x04);
			writeToTRANRAM(dummybyte);
		}while((sendTRANRAM()&0xFF)!=0xF0);
		
		return readBuffer(4);							// data is in converters buffer
	}
	
	
	/**
	 * Schreiben in ein Register des VL6180X Sensors.
	 * @param addr
	 * 			Slave Device Address des Sensors.
	 * @param reg
	 * 			Register des Sensors.
	 * @param value
	 * 			Zu schreibender Wert.
	 */
	private void writeReg8(int addr, int reg, int value){
		writeToTRANRAM((byte)0x00);							// spi command for write to an I2C slave
		writeToTRANRAM((byte)0x03);							// amount of bytes to write
		writeToTRANRAM((byte)((addr) << 1));				// I2C masterwrite operation, l-shift the actual slave address
		writeToTRANRAM((byte)((reg >> 8) & 0xFF));			// MSB of the slave address register
		writeToTRANRAM((byte)(reg & 0xFF));					// LSB of the slave address register;
		writeToTRANRAM((byte)value);						// data
		sendTRANRAM();
		
		do{
		}while ((checkI2CStatus()&0xFF) != 0xF0);
	}
	
	private void writeReg16(int addr, int reg, int value){
		writeToTRANRAM((byte)0x00);							// spi command for write to an I2C slave
		writeToTRANRAM((byte)0x04);							// amount of bytes to write
		writeToTRANRAM((byte)((addr) << 1));				// I2C masterwrite operation, l-shift the actual slave address
		writeToTRANRAM((byte)((reg >> 8) & 0xFF));			// MSB of the slave address register
		writeToTRANRAM((byte)(reg & 0xFF));					// LSB of the slave address register;	reg addr: 0x212
		writeToTRANRAM((byte)((value >> 8) & 0xFF));		// data MSB
		writeToTRANRAM((byte)(value & 0xFF));				// data LSB
		sendTRANRAM();
		
		do{
		}while ((checkI2CStatus()&0xFF) != 0xF0);
	}
	
	
	
	/**
	 * Schreiben eines Bytes in den transfer RAM.
	 * @param data
	 * 		Zu transferierendes Byte.
	 */
	private void writeToTRANRAM(int data){
		US.PUT2(TRANRAM + tranOffset, (byte)data);				// write  byte to tranram // +1
		tranOffset+=2;											//+=2
	}
	
	/**
	 * Setzen des command RAMs für die entsprechenden transfers und anschliessendes senden.
	 */
	private byte sendTRANRAM(){

		byte val = (byte) (((tranOffset - 2) >> 1) + 1); 
		byte ret = (byte)(tranOffset-1);
		
		US.PUT1(SPSR, 0x0);

		for(int i = 0; i < val; i++){
			US.PUT1(COMDRAM + i, 0xBB); 					// Eight bits, PCS2 low 0x0B 	// 0x8B = keep chip select after transfer		//0xBX : continue, delay after transfer, delay before transfer
		}
		US.PUT1(COMDRAM + val, 0x3B); 						// Eight bits, PCS2 low 0x0B
		US.PUT2(SPCR2, (val-1) << 8);
		
		tranOffset = 0;
		US.PUT2(SPCR1, 0x8000); 							// Enable SPI
		while ((US.GET1(SPSR) & 0x80) == 0);				// Wait for SPIF flag
		byte value = US.GET1(RECRAM+ret);
		return (byte) (value&0xFF);
	}
	
	/**
	 * Abfrage des I2C Bus Status.
	 * @return
	 * 		0xF0 -> i.O.,  0xF3 -> busy.
	 */
	public byte checkI2CStatus(){					
		writeToTRANRAM((byte)0x21);
		writeToTRANRAM((byte)0x04);
		writeToTRANRAM((byte)dummybyte);
		return sendTRANRAM();
		
	}
	
	/**
	 * Auslesen des Buffers des SPI-I2C Converter.
	 * @param numberByte
	 * 			Anzahl Byte(s) der/die ausgelesen werden sollen.
	 * @return
	 * 			Wert des Buffers.
	 */
	private int readBuffer(int numberByte){
		writeToTRANRAM((byte)0x06);								// read buffer of SC18IS600
		for(int i = 0; i < numberByte; i++){
			writeToTRANRAM(dummybyte);							// dummy byte - data should be read in MISO
		}
		byte val = sendTRANRAM();
		
		int ret = 0;
		
		switch(numberByte){
		case 4:
			ret |= (US.GET1(RECRAM+3)<<24);
			ret |= (US.GET1(RECRAM+5)<<16);
			ret |= (US.GET1(RECRAM+7)<<8);
			ret |= (US.GET1(RECRAM+9));
			break;
		case 2:
			ret |= (US.GET1(RECRAM+3)<<8);
			ret |= US.GET1(RECRAM+5);
			break;
		case 1:
			ret |= US.GET1(RECRAM+3);
			break;
		}
		
		return ret;
	}
	
// ----------------------------------------------------- !read / write to VL6180X -----------------------------------------------------------------------------------------------
	
//-------------------------------------TEST FUNCTIONS------------------------------------------------------------------------------------------
	
	/**
	 * Beschreibung des VL6180X Range__Result_Status Registers. Genauere Beschreibung ist dem Datenblatt zu entnehmen.
	 * @param val
	 * 		Wert des Registers.
	 * @return
	 * 		Beschreibung des Fehlercodes.
	 */
	public String errorCode(byte val) {
		System.out.print("Error:\t");
		byte code = (byte) ((val >> 4) & 0x0F);
		switch(code){
		case 0:
			return "No Error";
		case 1:
			return "VCSEL Continuity Test";
		case 2:
			return "VCSEL Watchdog Test";
		case 3:
			return "VCSEL Watchdog";
		case 4:
			return "PLL1 Lock";
		case 5:
			return "PLL2 Lock";
		case 6:
			return "Early Convergence Estimate, nothing in Sensors FOV";
		case 7:
			return "Max Convergence, nothing in Sensors FOV";
		case 8:
			return "No Target Ignore, nothing in Sensors FOV";
		case 11:
			return "Max Signal To Noise Ratio";
		case 12:
			return "Raw Ranging Algo Underflow";
		case 13:
			return "Raw Ranging Algo Overflow";
		case 14:
			return "Ranging Algo Underflow";
		case 15:
			return "Ranging Algo Overflow";
		default:
			return "";
		}
	}
	
	/**
	 * Testfunktion für die Überprüfung der SPI-Kommunikation.
	 * @return
	 */
	public byte testMiso(){
		// read IOConfig
		writeToTRANRAM((byte)0x21);
		writeToTRANRAM((byte)0x00);							
		writeToTRANRAM(dummybyte);
		return sendTRANRAM();
	}
	
	
//--------------------------------------------TEST FUNCTIONS----------------------------------------------------------------------------------	
}
