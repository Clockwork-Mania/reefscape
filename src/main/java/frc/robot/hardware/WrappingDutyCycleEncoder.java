package frc.robot.hardware;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class WrappingDutyCycleEncoder extends DutyCycleEncoder {
    int revs;
    double last_pos;

    public WrappingDutyCycleEncoder(int port) {
        super(port);
        revs = 0;
        last_pos = get();
    }

    public void periodic() {
        double currentPos = get();
        if(currentPos-last_pos > .8) {
            // underflow
            --revs;
        }
        else if(currentPos-last_pos < -.8) {
            // overflow
            ++revs;
        }
        last_pos = currentPos;
    }

    public double get() {
        return super.get()+revs*0.8;
    }

    public double getRaw() {
        return super.get();
    }

    public void reset() {
        revs = 0;
        last_pos = get();
    }
}