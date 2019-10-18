package lk.nsbm.dep.booking.db;

import Ik.ijse.dep.crypto.Crypto;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConnection {
    public static String host;
    public static String db;
    public static String username;
    public static String password;
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties properties = new Properties();
            File file = new File("D:\\IJSE\\FirstProject\\BookingSystem\\Booking\\resources\\application.properties");
            //D:\IJSE\FirstProject\BookingSystem\Booking\resources\application.properties
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();

            String ip = properties.getProperty("pos.ip");
            DBConnection.host = ip;
            String port = properties.getProperty("pos.port");
            String db = properties.getProperty("pos.db");
            DBConnection.db = db;
            String user = Crypto.decode(properties.getProperty("pos.user"), "123");
            DBConnection.username = user;
            String password = Crypto.decode(properties.getProperty("pos.password"), "123");
            DBConnection.password = password;

            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?createDatabaseIfNotExist=true&allowMultiQueries=true", user, password);
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()) {
                String sql = "\n" +
                        "create table `logtable`(\n" +
                        "   `userId` varchar(6) primary key not null ,\n" +
                        "   `userName` varchar(20),\n" +
                        "   `pasWord` varchar(20)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "create table `driver`(\n" +
                        "   `drvId` varchar(6) primary key not null ,\n" +
                        "   `drvName` varchar(20),\n" +
                        "   `drvNic` varchar(10),\n" +
                        "   `drvConNum` varchar(10)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "create table `conductor`(\n" +
                        "`conId` varchar(6)primary key not null ,\n" +
                        "`conName` varchar(20),\n" +
                        "`conNic` varchar(10),\n" +
                        "`conConNum` varchar(10)\n" +
                        ")ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "create table `bus`(\n" +
                        "`busId` varchar(6)primary key not null ,\n" +
                        "`busNo` varchar(20),\n" +
                        "`seat` int(5),\n" +
                        "`route` varchar(20),\n" +
                        "`drvId` varchar(6),\n" +
                        "`conId` varchar(6),\n" +
                        "constraint `FK_Driver` FOREIGN KEY (`drvID`) REFERENCES `driver`(`drvId`),\n" +
                        "constraint `FK_Conductor` FOREIGN KEY (`conId`) REFERENCES `conductor`(`conId`)\n" +
                        ")ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "create table `passenger`(\n" +
                        "`passId` varchar(6)primary key not null ,\n" +
                        "`passName` varchar(20),\n" +
                        "`passNic` varchar(10),\n" +
                        "`passConNum` varchar(10)\n" +
                        ")ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
                        "\n" +
                        "create table `ticket`(\n" +
                        "`ticketNo` varchar(6)not null,\n" +
                        "`passId` varchar(6)not null ,\n" +
                        "`busId` varchar(6)not null ,\n" +
                        "`date` date,\n" +
                        "`tripNo` varchar(6),\n" +
                        "`seatNo` int(2),\n" +
                        "`price` int(10),\n" +
                        "primary key (`passId`,`busId`),\n" +
                        "constraint `FK_passenger` FOREIGN KEY (`passId`) REFERENCES `passenger`(`passId`),\n" +
                        "constraint `FK_bus` FOREIGN KEY (`busId`) REFERENCES `bus`(`busId`)\n" +
                        ")ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
                pstm = connection.prepareStatement(sql);
                pstm.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return (dbConnection == null) ? (dbConnection = new DBConnection()) : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
