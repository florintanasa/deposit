package com.company.deposit.web.screens.depositmedterm;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FileDescriptorResource;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.screen.*;
import com.company.deposit.entity.Depositmedterm;

import javax.inject.Inject;

@UiController("deposit_Depositmedterm.browse")
@UiDescriptor("depositmedterm-browse.xml")
@LookupComponent("depositmedtermsTable")
@LoadDataBeforeShow
public class DepositmedtermBrowse extends StandardLookup<Depositmedterm> {

    @Inject
    protected GroupTable<Depositmedterm> depositmedtermsTable;

    @Inject
    protected UiComponents uiComponents;



    @Subscribe
    protected void onInit(InitEvent event) {
        depositmedtermsTable.addGeneratedColumn("qrcode", this::renderQrCodeImageComponent);
    }

    private Component renderQrCodeImageComponent(Depositmedterm depositmedterm) {
        FileDescriptor imageFile = depositmedterm.getQrcode();

        if (imageFile == null) {
            return null;
        }

        Image image = smallQrCodeImage();
        image.setSource(FileDescriptorResource.class).setFileDescriptor(imageFile);
        return image;
    }

    private Image smallQrCodeImage() {
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("60");
        image.setWidth("60");
        image.setStyleName("qrcode-icon-small");
        return image;
    }
}