package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Constants {

    public static class PneumaticConstants {
        // TODO verify these constants
        public static final int kPcm0Id = 0;
        public static final PneumaticsModuleType kPcmType = PneumaticsModuleType.CTREPCM;

        public static enum PneumaticIDs {
            eSolenoid0, eSolenoid1, eSolenoid2, eSolenoid3, eSolenoid4, eSolenoid5;
            public int get() { return ordinal(); }
        }
    }
    
}
