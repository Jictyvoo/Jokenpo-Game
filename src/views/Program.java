package views;


import controllers.JokenpoUsecase;
import models.ActionResult;
import utils.PlayChoice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Program extends JFrame {

    private static PlayChoice userChoice;
    private static final JokenpoUsecase usecase = new JokenpoUsecase();
    private String userSelection = null;
    private String pcSelection = null;
    private String result;

    /**
     * Launch the application.
     */

    private void updateUserSelection() {
        userSelection = "Voce: " + userChoice.text + "\n";
    }

    private String calculateResult() {
        Random randomGenerator = new Random();
        final PlayChoice randomChoice = usecase.fromNumber(randomGenerator.nextInt(3));
        updateUserSelection();
        pcSelection = "Computador: " + randomChoice.text + "\n";

        final ActionResult actionResult = usecase.execute(userChoice, randomChoice);
        switch (actionResult.result) {
            case DRAW:
                result = actionResult.result.name();
                break;
            case WIN:
                result = "You WIN";
                break;
            case LOSE:
                result = "You LOSE";
                break;
        }
        return actionResult.reason;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Program frame = new Program();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Program() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 235);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        userChoice = PlayChoice.ROCK;

        JLabel lblPcSelectThis = new JLabel("Computador: ");
        lblPcSelectThis.setBounds(254, 79, 170, 14);
        contentPane.add(lblPcSelectThis);

        JLabel lblYouSelectThis = new JLabel("Voce: ");
        lblYouSelectThis.setBounds(254, 45, 170, 14);
        contentPane.add(lblYouSelectThis);

        buildUserSelectionButtons(lblYouSelectThis, contentPane);

        JLabel lblResult = new JLabel("Resultado");
        lblResult.setBounds(254, 113, 135, 14);
        contentPane.add(lblResult);

        JLabel lblFinalMessage = new JLabel("Nenhuma partida jogada");
        lblFinalMessage.setBounds(20, 172, 370, 14);
        contentPane.add(lblFinalMessage);

        JButton btnPlay = new JButton("Play");
        btnPlay.addActionListener(e -> {
            String finalMessage = calculateResult();
            lblPcSelectThis.setText(pcSelection);
            lblYouSelectThis.setText(userSelection);
            lblResult.setText(result);
            lblFinalMessage.setText(finalMessage);
        });
        btnPlay.setBounds(143, 41, 101, 91);
        contentPane.add(btnPlay);

        JLabel lblSelecioneSuaJogada = new JLabel("Selecione sua jogada");
        lblSelecioneSuaJogada.setBounds(10, 11, 189, 14);
        contentPane.add(lblSelecioneSuaJogada);
    }

    private void buildUserSelectionButtons(JLabel lblYouselectthis, JPanel contentPane) {
        JButton btnRock = new JButton("Pedra");
        btnRock.addActionListener(e -> {
            userChoice = PlayChoice.ROCK;
            updateUserSelection();
            lblYouselectthis.setText(userSelection);
        });
        btnRock.setBounds(10, 41, 89, 23);
        contentPane.add(btnRock);

        JButton btnPaper = new JButton("Papel");
        btnPaper.addActionListener(e -> {
            userChoice = PlayChoice.PAPER;
            updateUserSelection();
            lblYouselectthis.setText(userSelection);
        });
        btnPaper.setBounds(10, 75, 89, 23);
        contentPane.add(btnPaper);

        JButton btnScissor = new JButton("Tesoura");
        btnScissor.addActionListener(e -> {
            userChoice = PlayChoice.SCISSOR;
            updateUserSelection();
            lblYouselectthis.setText(userSelection);
        });
        btnScissor.setBounds(10, 109, 89, 23);
        contentPane.add(btnScissor);
    }
}
