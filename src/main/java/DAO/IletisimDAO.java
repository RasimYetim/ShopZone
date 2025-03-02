package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.ConnectionManager;
import DAO.Iletisim;

public class IletisimDAO {
    private Connection con=ConnectionManager.getConnection();
    public IletisimDAO() throws SQLException {};

    public List<Iletisim> FormlariGetir(){
        List<Iletisim> liste=new ArrayList<>();
        String sql = "select * from Iletisim";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Iletisim mesaj=new Iletisim();
                mesaj.setAd(rs.getString("ad"));
                mesaj.setEmail(rs.getString("email"));
                mesaj.setKonu(rs.getString("konu"));
                mesaj.setMesaj(rs.getString("mesaj"));
            }

        }catch (Exception e){



        }catch (Error e){}



        return liste;


    }



}
