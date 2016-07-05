
import java.net.*;
import java.io.*;

public class TestDireccion {

    public String obtenerDeURL(String url) {
        String entrada = "", lin;
        try {
            URL pagina = new URL(url);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(pagina.openStream()))) {
                while ((lin = in.readLine()) != null) {
                    entrada += lin + "\n";
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return entrada;
    }

    public String obtieneDirecion(String lat, String lon) {
        String formatoJSON = "";
        try {
            formatoJSON = obtenerDeURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lon + "&sensor=true_or_false");
            String lin[] = formatoJSON.split("formatted_address");
            String dir;
            if (lin.length>1) {
                 dir = lin[1].split("\"")[2];
            }else{
                //System.out.println("Google Bloqueado");
                Thread.sleep(1000);
                return obtieneDirecion(lat, lon);
                
            }            
            return dir;
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
                return obtieneDirecion(lat, lon);
            } catch (InterruptedException ex) {
                return ex+"";
            }

        }

    }

    public String obtieneDirecionCompleta(String lat, String lon) {
        String formatoJSON = obtenerDeURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lon + "&sensor=true_or_false");
        String lin[] = formatoJSON.split("formatted_address");
        String dirCom = "";
        for (int i = 1; i < lin.length; i++) {
            dirCom += lin[i].split("\"")[2];
            if (i < lin.length - 1) {
                dirCom += "\n";
            }
        }
        return dirCom;
    }

    public String[] obtieneLatLon(String dir) {
        dir = dir.replaceAll(" ", "%20");
        String formatoJSON = obtenerDeURL("http://maps.googleapis.com/maps/api/geocode/json?address=" + dir + "&sensor=true_or_false");
        String lin[] = formatoJSON.split("location");
        String coor[] = new String[2];
        coor[0] = (lin[1].split(":")[2].split(",")[0]).replaceAll(" ", "");
        coor[1] = (lin[1].split(":")[3].split(" ")[1]).replaceAll("\n", "");;
        return coor;
    }
}
