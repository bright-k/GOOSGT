package com.auction.sniper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by H on 2018. 11. 27.
 */
public class MainWindow extends JFrame {
    public static final String MAIN_WINDOW_NAME = "main window";
    public static final String SNIPER_STATUS_NAME = "sniper status";
    public static final String SNIPERS_TABLE_NAME = "snipers table";

    public static final String STATUS_LOST = "lost..";
    public static final String STATUS_JOINING = "joining..";
    public static final String STATUS_BIDDING = "bidding..";
    public static final String STATUS_WINNING = "winning..";
    public static final String STATUS_WON = "won..!";

    private final SnipersTableModel snipers = new SnipersTableModel();

    private final JLabel sniperStatus = createLabel(MainWindow.STATUS_JOINING);

    public MainWindow() {
        super("Auction Sniper");
        setName(MAIN_WINDOW_NAME);
        add(sniperStatus);
        fillContentPane(makeSnipersTable());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showStatus(String status) {
        sniperStatus.setText(status);
    }

    public void showStatusText(String statusText) {
        snipers.setStatusText(statusText);
    }

    private void fillContentPane(JTable snipersTable) {
        final Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(new JScrollPane(snipersTable), BorderLayout.CENTER);
    }

    private JTable makeSnipersTable() {
        final JTable snipersTable = new JTable(snipers);
        snipersTable.setName(SNIPERS_TABLE_NAME);
        return snipersTable;
    }

    private static JLabel createLabel(String initialText) {
        JLabel result = new JLabel(initialText);
        result.setName(SNIPER_STATUS_NAME);
        result.setBorder(new LineBorder(Color.BLACK.black));
        return result;
    }
}
