package com.example.ahmedkhaled.buzzels.WishlistFragment;

import com.example.ahmedkhaled.buzzels.Material.MaterialObject;

import java.util.List;

/**
 * Created by AhmedKhaled on 3/29/2017.
 */

public interface WishListView {

    void PutData(List<MaterialObject> data);

    void NoWishlist();

}
