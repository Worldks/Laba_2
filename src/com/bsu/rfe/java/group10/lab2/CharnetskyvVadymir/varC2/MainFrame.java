package com.bsu.rfe.java.group10.lab2.CharnetskyvVadymir.varC2;

import java.awt.image.BufferedImage;
import java.lang.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




//@SuppressWarnings("serial");  // Для чего надо это предупреждение?
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private Double Mem1 =  0.0;
    private Double Mem2 =  0.0;
    private Double Mem3 =  0.0;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;
    private JTextField textFieldResult;

    private JLabel imageLabel = new JLabel();

    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioButtonsForMemory = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemoryType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memoryId = 1;

    public Double calculate1(Double x, Double y, Double z) {
        return (Math.pow((Math.log(Math.pow(1 + x, 2)) + Math.cos(Math.PI * z*z*z)) , Math.sin(y)));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (Math.pow(Math.cos(Math.PI*x*x*x) + Math.log(1 + y*y), 0.25) + Math.pow(Math.E, x*x) + Math.pow(1/x, 0.5) + Math.pow(Math.cos(Math.E), y));
    }
    // Вопросик с картинками    ---------------------------------------------------------
    private String[] ImagePath = {"E:\\University\\Программирование\\Лабалаторные\\2 курс\\Формулы для второй лабы\\1.jpg", "E:\\University\\Программирование\\Лабалаторные\\2 курс\\Формулы для второй лабы\\2.bmp"};
    BufferedImage imageFunction;
    {
        try {
            imageFunction = ImageIO.read(new File(ImagePath[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                try {
                    MainFrame.this.imageFunction = ImageIO.read(new File(ImagePath[formulaId - 1]));
                    imageLabel.setIcon(new ImageIcon(imageFunction));
                } catch (IOException ex) {
                    Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButtonForMemory(String buttonName, final int memoryId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        radioButtonsForMemory.add(button);
        hboxMemoryType.add(button);
    }

    // Конструктор класса
    public MainFrame() {

        super("Вычисление формулы");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);

        hboxMemoryType.add(Box.createHorizontalGlue());
        addRadioButtonForMemory("Переменная 1", 1);
        addRadioButtonForMemory("Переменная 2", 2);
        addRadioButtonForMemory("Переменная 3", 3);
        radioButtonsForMemory.setSelected(radioButtonsForMemory.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        textFieldMem1 = new JTextField("0",15);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        textFieldMem2 = new JTextField("0",15);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        textFieldMem3 = new JTextField("0",15);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());

        Box hboxMemoryR = Box.createHorizontalBox();
        hboxMemoryR.add(Box.createHorizontalGlue());
        hboxMemoryR.add(textFieldMem1);
        hboxMemoryR.add(Box.createHorizontalStrut(10));
        hboxMemoryR.add(textFieldMem2);
        hboxMemoryR.add(Box.createHorizontalStrut(10));
        hboxMemoryR.add(textFieldMem3);
        hboxMemoryR.add(Box.createHorizontalGlue());

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(45));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(45));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result = 0.0;
                    if (formulaId==1) {
                        if (x == -1 || x == 0 || y == 0) {
                            System.out.println("Недопустимые значения переменных x != -1 x != 0 y != 0, Попробуйте дриге значения");
                            System.exit(-1);
                        } else {
                            result = calculate1(x, y, z);
                        }
                    }else{
                        if (x == -1 || x == 0 || y == 0) {
                            System.out.println("Недопустимые значения переменных x != -1 x != 0 y != 0, Попробуйте дриге значения");
                            System.exit(-1);
                        } else {
                            result = calculate2(x, y, z);
                        }
                    }
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(memoryId == 1){
                    Mem1 =  0.0;
                    textFieldMem1.setText("0");
                }
                else if(memoryId == 2){
                    Mem2 = 0.0;
                    textFieldMem2.setText("0");
                }
                else if(memoryId == 3){         // Можно просто else
                    textFieldMem3.setText("0");
                }
            }
        });

        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(memoryId == 1){
                    Mem1 = (double)Mem1 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem1.setText(Mem1.toString());
                    textFieldResult.setText(Mem1.toString());
                }
                else if(memoryId == 2){
                    Mem2 = (double)Mem2 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem2.setText(Mem2.toString());
                    textFieldResult.setText(Mem2.toString());
                }
                else if(memoryId == 3){
                    Mem3 = (double)Mem3 + Double.parseDouble(textFieldResult.getText());
                    textFieldMem3.setText(Mem3.toString());
                    textFieldResult.setText(Mem3.toString());
                }

            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonM);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        imageLabel.setIcon(new ImageIcon(imageFunction));
        Box hboxImage = Box.createHorizontalBox();
        hboxImage.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        hboxImage.add(Box.createHorizontalGlue());
        hboxImage.add(imageLabel);
        hboxImage.add(Box.createHorizontalGlue());

// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxImage);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxMemoryR);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        //установить вертикальную коробку в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
