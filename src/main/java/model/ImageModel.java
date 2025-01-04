package model;

import javax.swing.ImageIcon;
import java.util.List;
import java.util.ArrayList;

public class ImageModel {
    private final List<ImageIcon> imageList;

    public ImageModel() {
        imageList = new ArrayList<>();
    }

    public void addImage(ImageIcon image) {
        imageList.add(image);
    }

    public List<ImageIcon> getImageList() {
        return imageList;
    }

    public void clearImages() {
        imageList.clear();
    }
}

