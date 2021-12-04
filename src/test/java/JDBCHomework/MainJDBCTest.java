package JDBCHomework;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class MainJDBCTest {
    Connection conn;

    @Before
    public void setUp() throws Exception {
        //create h2 DB

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH", "sa", "");
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS accommodation_fare_relation");
            statement.execute("DROP TABLE IF EXISTS accommodation;");
            statement.execute("DROP TABLE IF EXISTS room_fare");

            statement.execute("CREATE TABLE IF NOT EXISTS accommodation\n" +
                    "(\n" +
                    "    id integer NOT NULL UNIQUE,\n" +
                    "    type character varying(32) NOT NULL,\n" +
                    "    bed_type character varying(32) NOT NULL,\n" +
                    "    max_guests integer,\n" +
                    "    description character varying(512),\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");");
            statement.execute(" CREATE TABLE IF NOT EXISTS room_fare\n" +
                    " (\n" +
                    "     id integer NOT NULL UNIQUE,\n" +
                    "     price integer NOT NULL,\n" +
                    "     season character varying(32) NOT NULL,\n" +
                    "     PRIMARY KEY (id)\n" +
                    " );");
            statement.execute("CREATE TABLE IF NOT EXISTS accommodation_fare_relation\n" +
                    " (\n" +
                    "     id integer NOT NULL,\n" +
                    "     id_accommodation integer NOT NULL,\n" +
                    "     id_room_fare integer NOT NULL,\n" +
                    "     CONSTRAINT \"accommodation_fare_relation\" FOREIGN KEY (id_accommodation)\n" +
                    "         REFERENCES \"accommodation\" (id)\n" +
                    "         ON UPDATE NO ACTION\n" +
                    "         ON DELETE NO ACTION,\n" +
                    "     CONSTRAINT \"room_fare\" FOREIGN KEY (id_room_fare)\n" +
                    "         REFERENCES \"room_fare\" (id)\n" +
                    "         ON UPDATE NO ACTION\n" +
                    "         ON DELETE NO ACTION\n" +
                    ");");
            insertDataAccommodation(conn);
            insertDataRoomFare(conn);
            insertDataRelation(conn);
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void insertDataRelation(Connection conn) throws SQLException {
        PreparedStatement psRelation = conn.prepareStatement("insert into accommodation_fare_relation(id, id_accommodation, id_room_fare) values (?, ?, ?)");
        insertRowRelation(psRelation, 1, 1, 1);
        insertRowRelation(psRelation, 2, 1, 2);
        insertRowRelation(psRelation, 3, 2, 1);
        insertRowRelation(psRelation, 4, 2, 4);
        insertRowRelation(psRelation, 5, 3, 3);
        insertRowRelation(psRelation, 6, 3, 2);
        insertRowRelation(psRelation, 7, 4, 6);
        insertRowRelation(psRelation, 8, 4, 5);
        insertRowRelation(psRelation, 9, 5, 6);
        insertRowRelation(psRelation, 10, 5, 5);

    }

    private void insertRowRelation(PreparedStatement psRelation, int id, int id_accommodation, int id_room_fare) throws SQLException {
        psRelation.setInt(1, id);
        psRelation.setInt(2, id_accommodation);
        psRelation.setInt(3, id_room_fare);
        psRelation.executeUpdate();
    }

    private void insertDataRoomFare(Connection conn) throws SQLException {
        PreparedStatement psRoomFare = conn.prepareStatement("insert into room_fare(id, price, season) values (?, ?, ?)");
        insertRowRoomFare(psRoomFare, 1, 150, "Winter");
        insertRowRoomFare(psRoomFare, 2, 200, "Summer");
        insertRowRoomFare(psRoomFare, 3, 100, "Winter");
        insertRowRoomFare(psRoomFare, 4, 300, "Summer");
        insertRowRoomFare(psRoomFare, 5, 500, "Summer");
        insertRowRoomFare(psRoomFare, 6, 250, "Winter");


    }

    private void insertRowRoomFare(PreparedStatement psRoomFare, int id, int price, String season) throws SQLException {
        psRoomFare.setInt(1, id);
        psRoomFare.setInt(2, price);
        psRoomFare.setString(3, season);
        psRoomFare.executeUpdate();
    }


    private void insertDataAccommodation(Connection conn) throws SQLException {
        PreparedStatement psAccommodation = conn.prepareStatement("insert into accommodation(id, type, bed_type, max_guests, description) values (?, ?, ?, ?, ?)");
        insertRowAccommodation(psAccommodation, 1, "Double Room", "Twin", 2, "mountain view");
        insertRowAccommodation(psAccommodation, 2, "Double Room", "Double", 2, "king size bed");
        insertRowAccommodation(psAccommodation, 3, "Single Room", "Single", 1, "private bathroom");
        insertRowAccommodation(psAccommodation, 4, "Large Room", "Double", 3, "extra child bed");
        insertRowAccommodation(psAccommodation, 5, "Apartment", "King Size and Sofa", 4, "private kitchen");

    }

    private void insertRowAccommodation(PreparedStatement psAccommodation, int id, String type, String bed_type, int max_guests, String description) throws SQLException {
        psAccommodation.setInt(1, id);
        psAccommodation.setString(2, type);
        psAccommodation.setString(3, bed_type);
        psAccommodation.setInt(4, max_guests);
        psAccommodation.setString(5, description);
        psAccommodation.executeUpdate();
    }

    @Test
    public void testSelectFromAccommodation() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from accommodation");
        ResultSet resultSet = ps.executeQuery();
        System.out.println('\n');
        while (resultSet.next()) {
            String type = resultSet.getString("type");
            int id = resultSet.getInt("id");
            int guests = resultSet.getInt("max_guests");
            System.out.println(id + " " + type + " " + guests + " guests");
        }

    }

    @Test
    public void testSelectFromRoomFare() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from room_fare");
        ResultSet resultSet = ps.executeQuery();
        System.out.println('\n');
        while (resultSet.next()) {
            String season = resultSet.getString("season");
            int id = resultSet.getInt("id");
            int value = resultSet.getInt("price");
            System.out.println(id + " " + season + " " + value);
        }

    }


    @Test
    public void testSelectByCriteria() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select accommodation.type, room_fare.season, room_fare.price from accommodation_fare_relation " +
                "inner join accommodation on accommodation_fare_relation.id_accommodation = accommodation.id " +
                "inner join room_fare on accommodation_fare_relation.id_room_fare = room_fare.id " +
                "where room_fare.price >= 200");
        ResultSet resultSet = ps.executeQuery();
        System.out.println('\n');
        while (resultSet.next()) {
            String season = resultSet.getString("season");
            int value = resultSet.getInt("price");
            String type = resultSet.getString("type");
            System.out.println("===> " + type + " during " + season + " is " + value);

        }
    }

}