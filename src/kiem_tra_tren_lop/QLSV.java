/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class QLSV extends javax.swing.JFrame {

    /**
     * Creates new form QLSV
     */
    List<SinhVien> _lstSinhViens = new ArrayList<>();
    String kynang[] = {"code", "ngủ", "cày game", "phượt thủ"};
    int _vitri = 0;

    public QLSV() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        rdonam.setSelected(true);
        addcbb();
        addList();
    }

    public void addList() {
        _lstSinhViens.add(new SinhVien("PH13968", "Võ Hữu Thông", "ngủ", "Thiếu tiền", 9.8, "nam"));
        _lstSinhViens.add(new SinhVien("PH25314", "Triển Ngay Thôi", "cày game", "Thiếu tình", 7.7, "nam"));
        _lstSinhViens.add(new SinhVien("PH34728", "Lê Thị Lệ Thủy", "phượt thủ", "Thiếu tiền", 8.8, "nữ"));
        table();
    }

    public void addcbb() {
        for (int i = 0; i < kynang.length; i++) {
            cbbSkill.addItem(kynang[i]);
        }
    }

    public void add() {
        try {
            for (int i = 0; i < _lstSinhViens.size(); i++) {
                if (_lstSinhViens.get(i).getMssv().equals(txtMa.getText())) {
                    JOptionPane.showMessageDialog(rootPane, "Mã sinh viên đã tồn tại!");
                    return;
                }
            }
            if (txtMa.getText().equals("") || txtHoten.getText().equals("") || txtDiem.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa điền đủ thông tin!");
            } else {
                SinhVien sv = new SinhVien();
                try {
                    if (txtMa.getText().length() == 7) {
                        sv.setMssv(txtMa.getText());
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Mã phải là 7 ký tự");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(rootPane, e);
                }
                sv.setHoten(txtHoten.getText());
                sv.setDiem(Double.parseDouble(txtDiem.getText()));
                sv.setSkill(cbbSkill.getSelectedItem().toString());
                if (!(ckMoney.isSelected()) && !(cklove.isSelected())) {
                    sv.setGhichu("");
                } else if (ckMoney.isSelected() && !(cklove.isSelected())) {
                    sv.setGhichu("Thiếu tiền");
                } else if (!(ckMoney.isSelected()) && cklove.isSelected()) {
                    sv.setGhichu("Thiếu tình");
                } else {
                    sv.setGhichu("Thiếu tình,Thiếu Tiền");
                }
                if (rdonam.isSelected()) {
                    sv.setGioitinh("nam");
                } else {
                    sv.setGioitinh("nữ");
                }
                _lstSinhViens.add(sv);
                table();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Không thể kết nối đến máy chủ!");
        }
    }

    public void table() {
        DefaultTableModel model = (DefaultTableModel) tbRun.getModel();
        model.setRowCount(0);
        for (SinhVien x : _lstSinhViens) {
            model.addRow(new Object[]{
                x.getMssv(), x.getHoten(), x.getDiem(), x.getSkill(), x.getGhichu(), x.getGioitinh()
            });
        }
        tbRun.setRowSelectionInterval(_vitri, _vitri);
    }

    public void lamMoi() {
        txtMa.setText("");
        txtHoten.setText("");
        txtDiem.setText("");
        cbbSkill.setSelectedIndex(0);
        rdonam.setSelected(true);
        ckMoney.setSelected(false);
        cklove.setSelected(false);
    }

    public void xoa() {
        int index = tbRun.getSelectedRow();
        try {
            if (_lstSinhViens.size() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Bảng đang rỗng!");
            } else if (index == -1) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn mục để xóa!");
            } else {
                _lstSinhViens = (List<SinhVien>) IOData.readObject(txtFile.getText());
                if (_lstSinhViens.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "File đang rỗng!");
                    return;
                } else {
                    _lstSinhViens.remove(index);
                    String file = txtFile.getText();
                    IOData.writeObject(file, _lstSinhViens);
                    table();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi: " + e);
        }
    }

    public void update() {
        if (_lstSinhViens.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Bảng đang rỗng!");
            return;
        }
        int index = tbRun.getSelectedRow();
        SinhVien sv = _lstSinhViens.get(index);
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn mục để cập nhật!");
        } else if (!(_lstSinhViens.get(index).getMssv().equals(txtMa.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Bạn không thể thay đổi mã sinh viên");
        } else {
            _lstSinhViens.remove(index);
            sv.setHoten(txtHoten.getText());
            sv.setDiem(Double.parseDouble(txtDiem.getText()));
            sv.setSkill(cbbSkill.getSelectedItem().toString());
            if (!(ckMoney.isSelected()) && !(cklove.isSelected())) {
                sv.setGhichu("");
            } else if (ckMoney.isSelected() && !(cklove.isSelected())) {
                sv.setGhichu("Thiếu tiền");
            } else if (!(ckMoney.isSelected()) && cklove.isSelected()) {
                sv.setGhichu("Thiếu tình");
            } else {
                sv.setGhichu("Thiếu tình,Thiếu Tiền");
            }
            if (rdonam.isSelected()) {
                sv.setGioitinh("nam");
            } else {
                sv.setGioitinh("nữ");
            }
            _lstSinhViens.add(sv);
            table();
        }
    }

    public void sortTen() {
        if (_lstSinhViens.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Danh sách đang rỗng!");
        } else {
            _lstSinhViens.sort((o1, o2) -> o1.getHoten().compareTo(o2.getHoten()));
        }
        table();
    }

    public void sortMark() {
        if (_lstSinhViens.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Danh sách đang rỗng!");
            return;
        } else {
            Collections.sort(_lstSinhViens, Comparator.comparing(SinhVien::getDiem));
            Collections.reverse(_lstSinhViens);
        }
        table();
    }

    public void saveFile() {
        try {
            if (_lstSinhViens.size() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Danh sách rỗng!");
            } else if (txtFile.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Tên File cần lưu không thể để trống!");
            } else {
                String file = txtFile.getText() + ".txt";
                IOData.writeObject(file, _lstSinhViens);
                JOptionPane.showMessageDialog(rootPane, "Lưu thành công!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi: " + e);
        }
    }

    public void openFile() {
        try {
            JFileChooser jfc = new JFileChooser();
            FileNameExtensionFilter text = new FileNameExtensionFilter("văn bản", "txt", "dat");
            jfc.setFileFilter(text);
            jfc.setMultiSelectionEnabled(false);

            int x = jfc.showDialog(this, "chọn file");
            if (x == JFileChooser.APPROVE_OPTION) {
                File f = jfc.getSelectedFile();

                txtFile.setText(f.toString());
                _lstSinhViens = (List<SinhVien>) IOData.readObject(f.getAbsolutePath());
                if (_lstSinhViens.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "File đang rỗng!");
                    return;
                }
                table();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRun = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        txtDiem = new javax.swing.JTextField();
        cbbSkill = new javax.swing.JComboBox<>();
        btupdate = new javax.swing.JButton();
        btadd = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btLammoi = new javax.swing.JButton();
        btOpen = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btSortten = new javax.swing.JButton();
        btSortDiem = new javax.swing.JButton();
        ckMoney = new javax.swing.JCheckBox();
        cklove = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        btTrai1 = new javax.swing.JButton();
        btTrai2 = new javax.swing.JButton();
        btPhai2 = new javax.swing.JButton();
        btPhai1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFile = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbRun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MSSV", "Tên", "Điểm", "Kỹ năng", "Ghi chú", "Giới tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRunMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRun);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("QLSV");

        jLabel3.setText("Mã mèo:");

        jLabel4.setText("Họ Văn Tèn:");

        jLabel5.setText("Điểm giả:");

        jLabel6.setText("Kỹ năng:");

        cbbSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSkillActionPerformed(evt);
            }
        });

        btupdate.setText("Cập nhật");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        btadd.setText("Thêm");
        btadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddActionPerformed(evt);
            }
        });

        btxoa.setText("Xóa");
        btxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoaActionPerformed(evt);
            }
        });

        btLammoi.setText("Làm mới");
        btLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLammoiActionPerformed(evt);
            }
        });

        btOpen.setText("Open file");
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });

        btSave.setText("Lưu file");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        btSortten.setText("sx Tên");
        btSortten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSorttenActionPerformed(evt);
            }
        });

        btSortDiem.setText("Sx Điểm");
        btSortDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSortDiemActionPerformed(evt);
            }
        });

        ckMoney.setText("Thiếu tiền");

        cklove.setText("Thiếu tình");

        jLabel7.setText("Giới tính:");

        buttonGroup1.add(rdonam);
        rdonam.setText("Nam");
        rdonam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        btTrai1.setText("|<");
        btTrai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrai1ActionPerformed(evt);
            }
        });

        btTrai2.setText("<<");
        btTrai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrai2ActionPerformed(evt);
            }
        });

        btPhai2.setText(">>");
        btPhai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPhai2ActionPerformed(evt);
            }
        });

        btPhai1.setText(">|");
        btPhai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPhai1ActionPerformed(evt);
            }
        });

        jLabel2.setText("...");

        jLabel8.setText("Tên file:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(rdonam)
                                            .addGap(18, 18, 18)
                                            .addComponent(rdonu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cbbSkill, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(55, 55, 55)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btLammoi)
                                                    .addComponent(btadd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btOpen, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(28, 28, 28)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btSortDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btSortten, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btupdate)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(ckMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(cklove, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btTrai1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btTrai2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btPhai2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btPhai1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLammoi)
                    .addComponent(jLabel3)
                    .addComponent(btOpen))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btadd)
                    .addComponent(btSave))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btxoa)
                            .addComponent(btSortten))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbbSkill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdonam)
                            .addComponent(rdonu))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btTrai2)
                            .addComponent(btTrai1)
                            .addComponent(jLabel2)
                            .addComponent(btPhai2)
                            .addComponent(btPhai1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btupdate)
                            .addComponent(btSortDiem))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckMoney)
                            .addComponent(cklove))))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdonamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonamActionPerformed

    private void cbbSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSkillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSkillActionPerformed

    private void btaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_btaddActionPerformed

    private void btLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLammoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btLammoiActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        // TODO add your handling code here:
        xoa();
    }//GEN-LAST:event_btxoaActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btupdateActionPerformed

    private void btSorttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSorttenActionPerformed
        // TODO add your handling code here:
        sortTen();
    }//GEN-LAST:event_btSorttenActionPerformed

    private void btSortDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSortDiemActionPerformed
        // TODO add your handling code here:
        sortMark();
    }//GEN-LAST:event_btSortDiemActionPerformed

    private void tbRunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRunMouseClicked
        // TODO add your handling code here:
        int index = tbRun.getSelectedRow();
        SinhVien sv = _lstSinhViens.get(index);
        txtMa.setText(sv.getMssv());
        txtHoten.setText(sv.getHoten());
        txtDiem.setText(String.valueOf(sv.getDiem()));
        for (int i = 0; i < kynang.length; i++) {
            if (sv.getSkill().equals(kynang[i])) {
                cbbSkill.setSelectedIndex(i);
            }
        }
        if (sv.getGhichu().equalsIgnoreCase("thiếu tiền")) {
            ckMoney.setSelected(true);
            cklove.setSelected(false);
        } else if (sv.getGhichu().equals("")) {
            ckMoney.setSelected(false);
            cklove.setSelected(false);
        } else if (sv.getGhichu().equalsIgnoreCase("thiếu tình")) {
            cklove.setSelected(true);
            ckMoney.setSelected(false);
        } else {
            ckMoney.setSelected(true);
            cklove.setSelected(true);
        }
        if (sv.getGioitinh().equals("nam")) {
            rdonam.setSelected(true);
        } else {
            rdonu.setSelected(true);
        }
    }//GEN-LAST:event_tbRunMouseClicked

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // TODO add your handling code here:
        saveFile();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btTrai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrai2ActionPerformed
        // TODO add your handling code here:
        if (_vitri <= 0) {
            _vitri = _lstSinhViens.size();
            _vitri += -1;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        } else {
            _vitri += -1;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        }
    }//GEN-LAST:event_btTrai2ActionPerformed

    private void btTrai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrai1ActionPerformed
        // TODO add your handling code here:
        if (_vitri <= 0) {
            _vitri = _lstSinhViens.size();
            _vitri += -2;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        } else if (_vitri == 1) {
            _vitri = _lstSinhViens.size() - 1;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        } else {
            _vitri += -2;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        }
    }//GEN-LAST:event_btTrai1ActionPerformed

    private void btPhai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPhai2ActionPerformed
        // TODO add your handling code here:
        if (_vitri >= _lstSinhViens.size() - 1) {
            _vitri = 0;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        } else {
            _vitri += 1;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        }
    }//GEN-LAST:event_btPhai2ActionPerformed

    private void btPhai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPhai1ActionPerformed
        // TODO add your handling code here:
        if (_vitri >= _lstSinhViens.size() - 1) {
            _vitri = 1;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        } else if (_vitri == _lstSinhViens.size() - 2) {
            _vitri = 0;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        } else {
            _vitri += 2;
            tbRun.setRowSelectionInterval(_vitri, _vitri);
        }
    }//GEN-LAST:event_btPhai1ActionPerformed

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        // TODO add your handling code here:
        openFile();
    }//GEN-LAST:event_btOpenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QLSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLammoi;
    private javax.swing.JButton btOpen;
    private javax.swing.JButton btPhai1;
    private javax.swing.JButton btPhai2;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSortDiem;
    private javax.swing.JButton btSortten;
    private javax.swing.JButton btTrai1;
    private javax.swing.JButton btTrai2;
    private javax.swing.JButton btadd;
    private javax.swing.JButton btupdate;
    private javax.swing.JButton btxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbSkill;
    private javax.swing.JCheckBox ckMoney;
    private javax.swing.JCheckBox cklove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tbRun;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMa;
    // End of variables declaration//GEN-END:variables
}
