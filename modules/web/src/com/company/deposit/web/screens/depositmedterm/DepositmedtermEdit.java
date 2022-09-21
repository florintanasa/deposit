package com.company.deposit.web.screens.depositmedterm;

import com.google.common.collect.Sets;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.export.ExportFormat;
import com.haulmont.cuba.gui.screen.*;
import com.company.deposit.entity.Depositmedterm;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import org.springframework.format.annotation.DateTimeFormat;


import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@UiController("deposit_Depositmedterm.edit")
@UiDescriptor("depositmedterm-edit.xml")
@EditedEntityContainer("depositmedtermDc")
@LoadDataBeforeShow
public class DepositmedtermEdit extends StandardEditor<Depositmedterm> {
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private DataManager dataManager;
    @Inject
    private FileUploadField imageqrcodeUpload;
    @Inject
    private Notifications notifications;
    @Inject
    private ExportDisplay exportDisplay;
    @Inject
    private Image imagineqrcode;
    @Inject
    private Button downloadImageqrcodeBtn;
    @Inject
    private Button clearImageqrcodeBtn;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        displayImage();
        updateImageButtons(getEditedEntity().getQrcode() != null);
    }

    @Subscribe("imageqrcodeUpload")
    public void onUploadFieldFileUploadSucceed(FileUploadField.FileUploadSucceedEvent event) {
        File file = fileUploadingAPI.getFile(imageqrcodeUpload.getFileId());
        if(file != null) {
            notifications.create().withCaption("Fișierul este încărcat temporar la" + file.getAbsolutePath()).show();
        }

        FileDescriptor fd = imageqrcodeUpload.getFileDescriptor();
        try {
            fileUploadingAPI.putFileIntoStorage(imageqrcodeUpload.getFileId(), fd);
        } catch (FileStorageException e) {
            throw new RuntimeException("Eroare la salvarea fișierului în FileStorage", e);
        }
        dataManager.commit(fd);
        getEditedEntity().setQrcode(fd);
        displayImage();
        notifications.create().withCaption("Încarcă fișierul: " + imageqrcodeUpload.getFileName()).show();
    }

    @Subscribe("imageqrcodeUpload")
    public void onUploadFieldFileUploadError(UploadField.FileUploadErrorEvent event) {
        notifications.create().withCaption("Eroare la încărcare fișierului").show();
    }

    public void onGenerateQrCodeBtnClick() throws WriterException, IOException {
        //I got the current date and time
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentDate = LocalDate.now().atStartOfDay();
        String currentDateToday = dateFormat.format(currentDate);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTimeNow = dateTimeFormat.format(now);

        //the date necessary to input in QR code
        String data = getEditedEntity().getAccenumb() + "-" + getEditedEntity().getYearstore() + "-"
                + getEditedEntity().getDeposit() + "-" + dateTimeNow;


        //I check if the folders qrCodeImage exist in current users, if not exist I created this directory
        String currentUserHomeDir = System.getProperty("user.home");
        String qrCodeImageFolder = currentUserHomeDir + File.separator + "qrCodeImage";
        File theDir = new File(qrCodeImageFolder);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        //I set the file name for qrCodeImage
        String fileName = getEditedEntity().getAccenumb() + "-" + getEditedEntity().getYearstore() + "-"
                + getEditedEntity().getDeposit();
        String qrCodeImageFile = qrCodeImageFolder + File.separator + currentDateToday+ "-" + fileName + ".jpg";

        //encode the data in format QR_CODE with width 500 and heigh 500
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);

        //Write the matrix in the path
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(qrCodeImageFile));

        //Notification for user
        notifications.create().withCaption("Codul QR creat cu succes").show();
    }

    public void onDownloadImageBtnClick(){
        if( getEditedEntity().getQrcode() != null)
            exportDisplay.show(getEditedEntity().getQrcode(), ExportFormat.OCTET_STREAM);
    }

    public void onClearImageBtnClick(){
        getEditedEntity().setQrcode(null);
        displayImage();
    }

    public void displayImage() {
        if (getEditedEntity().getQrcode() !=null ) {
            imagineqrcode.setSource(FileDescriptorResource.class).setFileDescriptor(getEditedEntity().getQrcode());
            imagineqrcode.setVisible(true);
        } else {
            imagineqrcode.setVisible(false);
        }
    }

    public void updateImageButtons(boolean enable) {
        downloadImageqrcodeBtn.setEnabled(enable);
        clearImageqrcodeBtn.setEnabled(enable);
    }
}