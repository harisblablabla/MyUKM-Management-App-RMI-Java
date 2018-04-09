/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementukmserver;

import config.koneksi;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.Coach;
import model.LoginAdmin;
import model.LoginUser;
import model.Place;
import model.Schedule;
import model.StudentUKM;
import model.UKM;
import object.ICoach2;
import object.ILoginAdmin;
import object.ILoginUser;
import object.IPlace;
import object.ISchedule;
import object.IStudentUKM;
import object.IUKM;

/**
 *
 * @author Haris
 */
public class ManagementUKMServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        koneksi kon = new koneksi();
        kon.openConnection();
        try {
            Registry registry = LocateRegistry.createRegistry(1097);          
            ILoginUser obj = new LoginUser();
            ILoginAdmin obj2 = new LoginAdmin();
            ICoach2 obj3 = new Coach();
            ISchedule obj4 = new Schedule();
            IPlace obj5 = new Place();
            IUKM obj6 = new UKM();
            IStudentUKM obj7 = new StudentUKM();
            registry.rebind("log", obj);
            registry.rebind("log2", obj2);
            registry.rebind("log3", obj3);
            registry.rebind("log4", obj4);
            registry.rebind("log5", obj5);
            registry.rebind("log6", obj6);
            registry.rebind("log7", obj7);
            System.out.println("Object is registered.");
            System.out.println("Now server is waiting for client request...");
        }
        catch(RemoteException e) {
            System.out.println("FileServer: " + e);
        }
    }
    
}
