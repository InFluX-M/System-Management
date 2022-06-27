package com.example.finalpr;

import com.example.finalpr.Availabilities.BankCheck;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class BankCheckCell extends ListCell<BankCheck> {

    public final BankCheckItemCLR bankCheckItemCLR = new BankCheckItemCLR();
    final Node view = bankCheckItemCLR.getView();

    @Override
    protected void updateItem(BankCheck bankCheck, boolean b) {
        super.updateItem(bankCheck, b);

        if(b) {
            setGraphic(null);
        }
        else {
            bankCheckItemCLR.setCheck(bankCheck);
            setGraphic(view);
        }
    }

}
