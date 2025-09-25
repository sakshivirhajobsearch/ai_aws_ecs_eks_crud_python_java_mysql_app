package com.ai.aws.ecs.eks.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

// OkHttp for HTTP requests
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UnifiedGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTable ecsTable;
	private JTable eksTable;

	public UnifiedGUI() {
		setTitle("AI + AWS ECS & EKS CRUD Operations");
		setLayout(new GridLayout(2, 1));

		// ECS Section
		JPanel ecsPanel = new JPanel(new BorderLayout());
		ecsPanel.setBorder(BorderFactory.createTitledBorder("ECS Details"));
		ecsTable = new JTable(new DefaultTableModel(new Object[] { "Cluster Name", "Status" }, 0));
		ecsPanel.add(new JScrollPane(ecsTable), BorderLayout.CENTER);
		JButton ecsRefreshButton = new JButton("Refresh ECS");
		ecsRefreshButton.addActionListener(this::refreshECS);
		ecsPanel.add(ecsRefreshButton, BorderLayout.SOUTH);

		// EKS Section
		JPanel eksPanel = new JPanel(new BorderLayout());
		eksPanel.setBorder(BorderFactory.createTitledBorder("EKS Details"));
		eksTable = new JTable(new DefaultTableModel(new Object[] { "Cluster Name", "Version" }, 0));
		eksPanel.add(new JScrollPane(eksTable), BorderLayout.CENTER);
		JButton eksRefreshButton = new JButton("Refresh EKS");
		eksRefreshButton.addActionListener(this::refreshEKS);
		eksPanel.add(eksRefreshButton, BorderLayout.SOUTH);

		add(ecsPanel);
		add(eksPanel);

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void refreshECS(ActionEvent e) {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url("http://localhost:5000/api/ecs").build();

			Response response = client.newCall(request).execute();
			String jsonData = response.body().string();

			// TODO: Replace with real JSON parsing
			DefaultTableModel model = (DefaultTableModel) ecsTable.getModel();
			model.setRowCount(0);
			model.addRow(new Object[] { "ExampleCluster", "ACTIVE" });

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Failed to fetch ECS data: " + ex.getMessage());
		}
	}

	private void refreshEKS(ActionEvent e) {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url("http://localhost:5000/api/eks").build();

			Response response = client.newCall(request).execute();
			String jsonData = response.body().string();

			// TODO: Replace with real JSON parsing
			DefaultTableModel model = (DefaultTableModel) eksTable.getModel();
			model.setRowCount(0);
			model.addRow(new Object[] { "ExampleEKS", "1.27" });

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Failed to fetch EKS data: " + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(UnifiedGUI::new);
	}
}
