package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import frc.lib.AftershockSubsystem;

import static frc.robot.Constants.PneumaticConstants.*;

public class PneumaticSubsystem extends AftershockSubsystem {

    private Compressor mCompressor;
    private static PneumaticSubsystem mInstance;

    private Solenoid mSolenoid0, mSolenoid1, mSolenoid2, mSolenoid3, mSolenoid4, mSolenoid5;
    private Solenoid[] mSolenoids = {mSolenoid0, mSolenoid1, mSolenoid2, mSolenoid3, mSolenoid4, mSolenoid5};
    //private long[] times = new long[6];

    private PneumaticSubsystem() {
        mCompressor = new Compressor(kPcm0Id, kPcmType);
        for (int i = 0; i < mSolenoids.length; i++) {
            mSolenoids[i] = new Solenoid(kPcm0Id, kPcmType, i);
        }
    }

    @Override
    public void initialize() {
        mCompressor.enableDigital();
    }

    @Override
    public void periodic() {
        // for (int i = 0; i < mSolenoids.length; i++) {
        //     Solenoid solenoid = mSolenoids[i];
        //     if (solenoid.get()) {
        //         if ((System.currentTimeMillis() - times[i] > 1000) && (times[i] > 0)) {
        //             solenoid.set(false); // turn solenoid off TODO check if single fire
        //             times[i] = -1;
        //         }
        //     }
        // }
    }

    public void startCompessor() {
        mCompressor.enableDigital();
    }

    public void stopCompressor() {
        mCompressor.disable();
    }

    public void setSolenoid(PneumaticIDs id, boolean on) {
        Solenoid mSolenoid = mSolenoids[id.get()];
        mSolenoid.set(on);
        //times[id.get()] = System.currentTimeMillis();
    }

    @Override
    public void outputTelemetry() {
        
    }

    public static synchronized PneumaticSubsystem getInstance() {
        if (mInstance == null) mInstance = new PneumaticSubsystem();
        return mInstance;
    }
    
}
