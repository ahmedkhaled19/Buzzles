package com.example.ahmedkhaled.buzzels.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AhmedKhaled on 3/28/2017.
 */

public interface MOPView {

    void setupDots(int page, int size);

    void setData(List<MOPobject> data);
}
