package eg.edu.alexu.csd.paint2D;


import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class ClassLoading {
   private String path;
    private Constructor [] constructor;
   public ClassLoading(String path) {
      
         this.path=path;
       File file = new File(path);
       try {
           URL url = file.toURI().toURL();
           URL[] urls = new URL[] { url };
           @SuppressWarnings("resource")
           ClassLoader cl = new URLClassLoader(urls);
           @SuppressWarnings("rawtypes")
           Class cls = cl.loadClass("eg.edu.alexu.csd.paint2D.B");
           constructor = cls.getConstructors();
       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "ERROR",
                   ButtonType.OK).show();      }
   }
   public Shapes getObject(Pane root){
       try{
           Object object = constructor[0].newInstance(root);
           return (Shapes) object;
       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "ERRORR",
                   ButtonType.OK).show();          }
       return null;
   }
}