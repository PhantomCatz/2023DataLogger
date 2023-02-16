package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.DataLogger.*;

import java.util.ArrayList;

public class Robot extends TimedRobot {
  public ArrayList<CatzLog> dataArrayList;
  public static DataCollection dataCollection;
  public static Timer dataCollectionTimer;
  
  @Override
  public void robotInit() {
    dataArrayList = new ArrayList<CatzLog>();
    dataCollection = new DataCollection();
    dataCollectionTimer = new Timer();
    dataCollection.dataCollectionInit(dataArrayList);
    dataCollectionTimer.start();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    dataCollectionTimer.reset();
    dataCollectionTimer.start();
    dataCollection.setLogDataID(dataCollection.chosenDataID.getSelected());
    dataCollection.startDataCollection();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {
    if (dataCollection.logDataValues == true)
    {
      dataCollection.stopDataCollection();
      
      try
      {
        dataCollection.exportData(dataArrayList);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void disabledPeriodic() {}
}