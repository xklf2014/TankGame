import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class ImageTest {
    @Test
    void test(){
        try {
            //BufferedImage image = ImageIO.read(new File(""));

            BufferedImage images = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
