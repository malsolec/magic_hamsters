package database;

/*
 * Smart Activities
 *     Copyright (C) 2016 magic-hamsters-team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DatabaseCreator {

    private static Schema schema;

    private static Entity NFCDevice;
    private static Entity kidActivity;
    private static Entity action;

    public static void main(String[] args) throws Exception {
        schema = new Schema(11, "database");
        createKidActivity();
        createNFCDevice();
        createAction();
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void createKidActivity() {
        kidActivity = schema.addEntity("KidActivity");
        kidActivity.addIdProperty();
        kidActivity.addStringProperty("name");
        kidActivity.addStringProperty("imgUrl");
        kidActivity.addIntProperty("orderNumber");
    }

    private static void createNFCDevice() {
        NFCDevice = schema.addEntity("NFCDevice");
        NFCDevice.addIdProperty();
        NFCDevice.addStringProperty("deviceId");

        Property kidActivityId = NFCDevice.addLongProperty("kidActivityId").getProperty();
        NFCDevice.addToOne(kidActivity, kidActivityId);
        kidActivity.addToOne(NFCDevice, kidActivityId);
    }




    private static void createAction() {
        action = schema.addEntity("Action");
        action.addIdProperty();
        action.addStringProperty("name");
        action.addStringProperty("imgUrl");
        action.addIntProperty("orderNumber");

        Property kidActivityId = action.addLongProperty("kidActivityId").getProperty();
        action.addToOne(kidActivity, kidActivityId);
        kidActivity.addToOne(action, kidActivityId);
    }

}
