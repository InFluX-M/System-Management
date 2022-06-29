package com.example.finalpr;

import com.example.finalpr.Availabilities.Loan;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class LoanCell extends ListCell<Loan> {

    public final LoanItemCLR loanItemCLR = new LoanItemCLR();
    final Node view = loanItemCLR.getView();

    @Override
    protected void updateItem(Loan loan, boolean b) {
        super.updateItem(loan, b);

        if(b) {
            setGraphic(null);
        }
        else {
            loanItemCLR.setLoan(loan);
            setGraphic(view);
        }
    }

}
