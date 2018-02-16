package org.usfirst.frc.team1923.shuffleboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SendablePriorityList extends SendableBase implements Sendable {

    private List<String> items;
    private NetworkTableEntry values;
    
    public SendablePriorityList(List<String> items) {
        this.items = items;
    }

    public List<String> getOrder() {
        if (this.values != null) {
            return Arrays.asList(values.getStringArray(new String[0]));
        } else {
            return Collections.emptyList();
        }
    }
    
    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addStringArrayProperty("values", () -> {
            return items.toArray(new String[0]);
        }, null);
        this.values = builder.getEntry("values");
    }

}
