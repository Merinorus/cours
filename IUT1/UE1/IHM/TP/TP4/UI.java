package tp4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI extends JFrame {
	public static void main(String[] args) {
		UI ui = new UI();
	}
	
	private TraceZone pnlTrace;
	private JPanel pnlExpr, pnlParams, pnlParamsFields, pnlParamsBtn;
	private JLabel lblExpr;
	private JTextField txtExpr;
	private JButton btnTrace;
	
	private JTextField[] txtParams;
	private JLabel[] lblParams;
	private String[] strParams = {"X min", "X max", "delta X",
								  "Y min", "Y max", "delta Y"};	
	
	public UI() {
		initPanels();
		addPanels();
		
		initComponents();
		addComponents();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void initComponents() {
		lblExpr = new JLabel("Expression à tracer: f(x) = ");
		txtExpr = new JTextField();
		
		btnTrace = new JButton("Tracer");
		btnTrace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					traceExpression();
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(pnlTrace, ex.getStackTrace());
				}
			}
		});
		
		lblParams = new JLabel[strParams.length];
		txtParams = new JTextField[strParams.length];
		for(int i = 0; i < strParams.length; i++) {
			lblParams[i] = new JLabel(strParams[i]);
			txtParams[i] = new JTextField("0.0");
		}
	}
	
	private void addComponents() {
		pnlExpr.add(lblExpr);
		pnlExpr.add(txtExpr);
		pnlParamsBtn.add(btnTrace);
		
		for(int i = 0; i < strParams.length; i++) {
			pnlParamsFields.add(lblParams[i]);
			pnlParamsFields.add(txtParams[i]);
		}
	}
	
	private void initPanels() {
		pnlTrace = new TraceZone();
		pnlTrace.setPreferredSize(new Dimension(300, 300));
		
		pnlExpr = new JPanel();
		pnlExpr.setLayout(new GridLayout(2,1));
		
		pnlParams = new JPanel();
		pnlParams.setLayout(new BorderLayout());
		pnlParamsBtn = new JPanel();
		pnlParamsBtn.setLayout(new FlowLayout());
		pnlParamsFields = new JPanel();
		pnlParamsFields.setLayout(new GridLayout(strParams.length,2));
		pnlParamsFields.setBorder(BorderFactory.createTitledBorder("Paramètres"));
	}
	
	private void addPanels() {
		add(pnlTrace, BorderLayout.CENTER);
		add(pnlParams, BorderLayout.WEST);
		add(pnlExpr, BorderLayout.SOUTH);
		
		pnlParams.add(pnlParamsFields, BorderLayout.CENTER);
		pnlParams.add(pnlParamsBtn, BorderLayout.SOUTH);
		add(pnlParams, BorderLayout.WEST);
	}
	
	private void traceExpression() throws Exception {
		try {
			double Xmin = Double.parseDouble(txtParams[0].getText()),
				   Xmax = Double.parseDouble(txtParams[1].getText()),
				   Xdel = Double.parseDouble(txtParams[2].getText()),
				   Ymin = Double.parseDouble(txtParams[3].getText()),
				   Ymax = Double.parseDouble(txtParams[4].getText()),
				   Ydel = Double.parseDouble(txtParams[5].getText());
				
			Analyzer analyzer = new Analyzer(txtExpr.getText());
			Expression expr = analyzer.analyze();
		
			pnlTrace.draw();
		} catch (Exception ex) {
			throw ex;
		}
	}
}