package ch.ntb.inf.deep.runtime.mpc555.driver;

import ch.ntb.inf.deep.runtime.mpc555.IntbMpc555HB;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.QSPI;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI18IS600;
import ch.ntb.inf.deep.runtime.mpc555.driver.VL6180XAddr;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.unsafe.US;

public class VL6180X extends Task implements IntbMpc555HB, VL6180XAddr {
	private boolean initDone = false;
	private int[] distances;
	private int numSensors;
	private int txPtr = 0;
	// buffers to copy to SPI
	private int[] buffer1 = new int[1];
	private int[] buffer2 = new int[2];
	private int[] buffer3 = new int[3];
	private int[] buffer4 = new int[4];
	
	private final byte I2C_DEFAULT_ADDR = 0x29;
	
	private boolean writing = false;
	private int currentSensor = 0;
	
	private MPIOSM_DIO testPin = new MPIOSM_DIO(5, true);
	private SCI18IS600 sci;
	
	/**
	 * Initialise all Sensors, also initialises the SPI peripheral and SCI18IS600
	 * @param numSensors number of sensors
	 * @param rstPinNum MPIOB (MPIOSM_DIO) pin connected to the SCI18IS600's reset pin
	 */
	public VL6180X(int numSensors, int rstPinNum)
	{
		this.numSensors = numSensors;
		distances = new int[numSensors];
		
		// init loop, in case we can't initialise all sensors properly, start again
		do {
			// init SPI
			QSPI.init();
						
			// init SCI18IS600
			sci = new SCI18IS600(rstPinNum);
			
			
			initDone = true;
			for (int i = 0; i < numSensors; i++)
			{
				if (!initSensor(i))
				{
					// initialisation failed, reset SCI
					initDone = false;
					break;
				}
				else
				{
					// initialisation successful, start continuous measurement
					startContBlocking(i+0x30);
				}
			}			
		} while(!initDone);
		testPin.set(false);
		period = 2;
		Task.install(this);
	}
	
	/**
	 * read sensor values
	 * @return distances in mm
	 */
	public int[] read()
	{
		return distances;
	}
	
	/**
	 * initialize sensor
	 * @param sensorIndex number of sensor (0..3)
	 * @return true if successful, false if failed
	 */
	private boolean initSensor(int sensorIndex)
	{
		int gpio = 0;
		
		// set corresponding GPIO pin high 
		/*switch(sensorIndex){
		case 3:
			gpio +=0x02;
		case 2:
			gpio +=0x04;	
		case 1:
			gpio +=0x08;
		case 0:
			gpio+=0x01;
		}
		*/
		
		switch(sensorIndex){
		case 2:
			//GPIO 1
			gpio += 0x02;
			
		case 1:
			//GPIO 2
			gpio += 0x04;
		case 3:
			//GPIO 3
			gpio +=0x08;
			
		case 0:
			//GPIO 0
			gpio += 0x01;
		}
		
		sci.writeRegBlocking(SCI18IS600.REG_GPIO_VAL, gpio);
		
		// check GPIO register
		
		if (sci.readRegBlocking(SCI18IS600.REG_GPIO_VAL) != gpio)
		{
			// setting GPIO failed, initialisation unsuccessful
			return false;
		}
		while(readRegBlocking(I2C_DEFAULT_ADDR, SYSTEM__FRESH_OUT_OF_RESET) != 1);
		if (!sci.I2COk())
		{
			return false;
		}
		
		//set new unique address
		writeRegBlocking(I2C_DEFAULT_ADDR, I2C_SLAVE__DEVICE_ADDRESS, 0x30+sensorIndex);
		if (!sci.I2COk())
		{
			return false;
		}
		
		initSensorData(0x30+sensorIndex);
		if (!sci.I2COk())
		{
			sci.printI2CStatus();
			return false;
		}
				
		return true;
	}
	
	/**
	 * start sensor in continuous mode
	 * blocks while data transfer is ongoing
	 * @param address VL6180X I2C address
	 */
	private void startContBlocking(int address)
	{
		writeRegBlocking(address, SYSRANGE__START, 3);
	}
	
	/**
	 * Task function to be called by tasking system
	 */
	public void action()
	{
		testPin.set(true);
		if (writing)
		{
			if (writeReg(currentSensor+0x30, SYSTEM__INTERRUPT_CLEAR, 7))
			{
				currentSensor++;
				writing = false;
				if (currentSensor >= numSensors)
				{
					currentSensor = 0;
				}
			}
		}
		else
		{
			if (readReg(currentSensor+0x30, RESULT__RANGE_VAL, buffer1))
			{
				writing = true;
				distances[currentSensor] = buffer1[0];
			}
		}
		testPin.set(false);
	}
	
	/**
	 * initialise sensor with proper data
	 * @param addr address of sensor
	 */
	private void initSensorData(int addr){						// DM00123019-short.pdf - Appendix 1: VL6180X Tuning Settings
		writeRegBlocking(addr, 0x0207, 0x01);
		writeRegBlocking(addr, 0x0208, 0x01);
		writeRegBlocking(addr, 0x0133, 0x01);
		writeRegBlocking(addr, 0x0096, 0x00);
		writeRegBlocking(addr, 0x0097, 0xFD); 								// RANGE_SCALER = 253
		writeRegBlocking(addr, 0x00E3, 0x00);
		writeRegBlocking(addr, 0x00E4, 0x04);
		writeRegBlocking(addr, 0x00E5, 0x02);
		writeRegBlocking(addr, 0x00E6, 0x01);
		writeRegBlocking(addr, 0x00E7, 0x03);
		writeRegBlocking(addr, 0x00F5, 0x02);
		writeRegBlocking(addr, 0x00D9, 0x05);
		writeRegBlocking(addr, 0x00DB, 0xCE);
		writeRegBlocking(addr, 0x00DC, 0x03);
		writeRegBlocking(addr, 0x00DD, 0xF8);
		writeRegBlocking(addr, 0x009F, 0x00);
		writeRegBlocking(addr, 0x00A3, 0x3C);
		writeRegBlocking(addr, 0x00B7, 0x00);
		writeRegBlocking(addr, 0x00BB, 0x3C);
		writeRegBlocking(addr, 0x00B2, 0x09);
		writeRegBlocking(addr, 0x00CA, 0x09);
		writeRegBlocking(addr, 0x0198, 0x01);
		writeRegBlocking(addr, 0x01B0, 0x17);
		writeRegBlocking(addr, 0x01AD, 0x00);
		writeRegBlocking(addr, 0x00FF, 0x05);
		writeRegBlocking(addr, 0x0100, 0x05);
		writeRegBlocking(addr, 0x0199, 0x05);
		writeRegBlocking(addr, 0x0109, 0x07);
		writeRegBlocking(addr, READOUT__AVERAGING_SAMPLE_PERIOD, 0x30);	// 0x10A
		writeRegBlocking(addr, 0x003F, 0x46);
		writeRegBlocking(addr, 0x01A6, 0x1B);
		writeRegBlocking(addr, 0x01AC, 0x3E);
		writeRegBlocking(addr, 0x01A7, 0x1F);
		writeRegBlocking(addr, 0x0103, 0x01);
		writeRegBlocking(addr, 0x0030, 0x00);
		writeRegBlocking(addr, SYSRANGE__INTERMEASUREMENT_PERIOD, 0x0A);	// 0x01B
		writeRegBlocking(addr, SYSALS__INTERMEASUREMENT_PERIOD, 0x0A); 	// 0x03E
		writeRegBlocking(addr, 0x0131, 0x04);
		writeRegBlocking(addr, SYSTEM__MODE_GPIO1, 0x10);					// 0x011
		writeRegBlocking(addr, SYSTEM__INTERRUPT_CONFIG_GPIO, 0x24);		// 0x014
		writeRegBlocking(addr, SYSRANGE__VHV_REPEAT_RATE, 0xFF);			// 0x031
		writeRegBlocking(addr, 0x00D2, 0x01);
		writeRegBlocking(addr, 0x00F2, 0x01);
		
		writeRegBlocking(addr, SYSALS__ANALOGUE_GAIN, 0x46);
		writeReg2Blocking(addr, SYSALS__INTEGRATION_PERIOD, 0x0063);
		writeRegBlocking(addr, SYSRANGE__VHV_RECALIBRATE, 0x01);
		writeRegBlocking(addr, SYSRANGE__MAX_CONVERGENCE_TIME, 0x32);
		writeRegBlocking(addr, INTERLEAVED_MODE__ENABLE, 0x00);
		writeRegBlocking(addr, SYSRANGE__RANGE_CHECK_ENABLES, 0x01);
		writeReg2Blocking(addr, SYSRANGE__EARLY_CONVERGENCE_ESTIMATE, 0x007B);
		writeRegBlocking(addr, FIRMWARE__RESULT_SCALER, 0x01);
		writeRegBlocking(addr, SYSRANGE__MAX_AMBIENT_LEVEL_MULT, 0x0A);
	}	
	
	/**
	 * read from VL6180X register
	 * blocks until read is done
	 * @param addr of sensor
	 * @param reg register address
	 * @return value in register
	 */
	private int readRegBlocking(int addr, int reg)
	{
		buffer2[0] = reg >> 8;
		buffer2[1] = reg & 0xFF;
		sci.I2CWriteReadBlocking(addr, buffer2, buffer1);
		return buffer1[0];
	}
	
	/**
	 * read from VL6180X register
	 * does not block
	 * @param addr address of sensor
	 * @param reg register address
	 * @param buffer buffer to return value in
	 * @return true if done (value valid), false if read still in progress
	 */
	private boolean readReg(int addr, int reg, int[] buffer)
	{
		buffer2[0] = reg >> 8;
		buffer2[1] = reg & 0xFF;
		return sci.I2CWriteRead(addr, buffer2, buffer);
	}
	
	/**
	 * write to VL6180X register
	 * does not block
	 * @param addr address of sensor
	 * @param reg register address
	 * @param value value to write
	 * @return true if write is done, false if write still in progress
	 */
	private boolean writeReg(int addr, int reg, int value)
	{
		buffer3[0] = reg >> 8;
		buffer3[1] = reg & 0xFF;
		buffer3[2] = value;
		return sci.I2CWrite(addr, buffer3);
	}
	
	/**
	 * write to VL6180X register
	 * blocks until write is done
	 * @param addr VL6180X address
	 * @param reg register address
	 * @param value value to write
	 */
	private void writeRegBlocking(int addr, int reg, int value)
	{
		buffer3[0] = reg >> 8;
		buffer3[1] = reg & 0xFF;
		buffer3[2] = value;
		sci.I2CWriteBlocking(addr, buffer3);
	}
	
	/**
	 * write to VL6180X register
	 * blocks until write is done
	 * writes 2 bytes rather than 1
	 * @param addr VL6180X address
	 * @param reg register address
	 * @param value value to write
	 */
	private void writeReg2Blocking(int addr, int reg, int value)
	{
		buffer4[0] = reg >> 8;
		buffer4[1] = reg & 0xFF;
		buffer4[2] = value >> 8;
		buffer4[3] = value & 0xFF;
		sci.I2CWriteBlocking(addr, buffer4);
	}
	
	
}
