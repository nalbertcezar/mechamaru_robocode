package nalbert_robot;

import robocode.*;
import java.awt.Color;

public class Mechamaru extends AdvancedRobot {
    
    boolean movingForward = true;
    
    public void run() {
	
        setColors(Color.black, Color.white, Color.yellow);
        
        while (true) {
		
            if (movingForward) {
                ahead(100);
            } else {
                back(100);
            }
            
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        double absBearing = getHeadingRadians() + e.getBearingRadians();
        double gunTurn = absBearing - getGunHeadingRadians();
        
        setTurnGunRightRadians(gunTurn);
        
        double distance = e.getDistance();
        if (distance < 200) {
            setFire(3);
        } else {
            setFire(1);
        }
        
        if (distance < 100) {
            if (movingForward) {
                back(50);
            } else {
                ahead(50);
            }
        }
    }

    public void onHitWall(HitWallEvent e) {
        movingForward = !movingForward;
        turnRight(90);
    }
}
