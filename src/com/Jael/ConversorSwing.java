package com.Jael;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorSwing extends JFrame {

    private JComboBox<String> comboOrigen;
    private JComboBox<String> comboDestino;
    private JTextField campoCantidad;
    private JLabel etiquetaResultado;

    public ConversorSwing() {
        setTitle("Conversor de Monedas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // ComboBox de monedas
        String[] monedas = {"USD", "MXN", "ARS", "EUR"};
        comboOrigen = new JComboBox<>(monedas);
        comboDestino = new JComboBox<>(monedas);

        // Campo de texto
        campoCantidad = new JTextField();

        // Botón
        JButton botonConvertir = new JButton("Convertir");

        // Etiqueta de resultado
        etiquetaResultado = new JLabel("Resultado: ", SwingConstants.CENTER);

        // Agregar componentes
        add(new JLabel("Moneda Origen:"));
        add(comboOrigen);

        add(new JLabel("Moneda Destino:"));
        add(comboDestino);

        add(new JLabel("Cantidad:"));
        add(campoCantidad);
        add(botonConvertir);
        add(etiquetaResultado);

        // Acción del botón
        botonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirMoneda();
            }
        });
    }

    private void convertirMoneda() {
        String origen = comboOrigen.getSelectedItem().toString();
        String destino = comboDestino.getSelectedItem().toString();
        double cantidad;

        try {
            cantidad = Double.parseDouble(campoCantidad.getText());
        } catch (NumberFormatException e) {
            etiquetaResultado.setText(" Ingresa una cantidad válida.");
            return;
        }

        double tasa = ApiService.obtenerTasaCambio(origen, destino);

        if (tasa > 0) {
            double resultado = cantidad * tasa;
            etiquetaResultado.setText(String.format("%.2f %s = %.2f %s", cantidad, origen, resultado, destino));
        } else {
            etiquetaResultado.setText(" No se pudo obtener la tasa.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConversorSwing app = new ConversorSwing();
            app.setVisible(true);
        });
    }
}
