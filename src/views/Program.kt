package views

import controllers.JokenpoUsecase
import utils.PlayChoice
import utils.PlayResults
import java.awt.EventQueue
import java.util.*
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class Program : JFrame() {
    private var userSelection: String? = null
    private var pcSelection: String? = null
    private var result: String? = null

    private fun updateUserSelection() {
        userSelection = """
             Voce: ${userChoice.text}
             
             """.trimIndent()
    }

    private fun calculateResult(): String {
        val randomGenerator = Random()
        val randomChoice = usecase.fromNumber(randomGenerator.nextInt(3))
        updateUserSelection()
        pcSelection = """
            Computador: ${randomChoice.text}
            
            """.trimIndent()
        val actionResult = usecase.execute(userChoice, randomChoice)
        result = when (actionResult.result) {
            PlayResults.DRAW -> actionResult.result.name
            PlayResults.WIN -> "You WIN"
            PlayResults.LOSE -> "You LOSE"
        }
        return actionResult.reason
    }

    private fun buildUserSelectionButtons(lblYouselectthis: JLabel, contentPane: JPanel) {
        val btnRock = JButton("Pedra")
        btnRock.addActionListener {
            userChoice = PlayChoice.ROCK
            updateUserSelection()
            lblYouselectthis.text = userSelection
        }
        btnRock.setBounds(10, 41, 89, 23)
        contentPane.add(btnRock)
        val btnPaper = JButton("Papel")
        btnPaper.addActionListener {
            userChoice = PlayChoice.PAPER
            updateUserSelection()
            lblYouselectthis.text = userSelection
        }
        btnPaper.setBounds(10, 75, 89, 23)
        contentPane.add(btnPaper)
        val btnScissor = JButton("Tesoura")
        btnScissor.addActionListener {
            userChoice = PlayChoice.SCISSOR
            updateUserSelection()
            lblYouselectthis.text = userSelection
        }
        btnScissor.setBounds(10, 109, 89, 23)
        contentPane.add(btnScissor)
    }

    companion object {
        private lateinit var userChoice: PlayChoice
        private val usecase = JokenpoUsecase()

        /**
         * Launch the application.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater {
                try {
                    val frame = Program()
                    frame.isVisible = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * Create the frame.
     */
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setBounds(100, 100, 450, 235)
        val contentPane = JPanel()
        contentPane.border = EmptyBorder(5, 5, 5, 5)
        contentPane.layout = null
        setContentPane(contentPane)
        userChoice = PlayChoice.ROCK

        val (lblPcSelectThis, lblYouSelectThis, lblResult) = buildSelectionLabels(contentPane)

        val lblFinalMessage = JLabel("Nenhuma partida jogada")
        lblFinalMessage.setBounds(20, 172, 370, 14)
        contentPane.add(lblFinalMessage)

        val btnPlay = JButton("Play")
        btnPlay.addActionListener {
            val finalMessage = calculateResult()
            lblPcSelectThis.text = pcSelection
            lblYouSelectThis.text = userSelection
            lblResult.text = result
            lblFinalMessage.text = finalMessage
        }
        btnPlay.setBounds(143, 41, 101, 91)
        contentPane.add(btnPlay)

        val lblSelectYourPlay = JLabel("Selecione sua jogada")
        lblSelectYourPlay.setBounds(10, 11, 189, 14)
        contentPane.add(lblSelectYourPlay)
    }

    private fun buildSelectionLabels(contentPane: JPanel): Triple<JLabel, JLabel, JLabel> {
        val lblPcSelectThis = JLabel("Computador: ")
        lblPcSelectThis.setBounds(254, 79, 170, 14)
        contentPane.add(lblPcSelectThis)

        val lblYouSelectThis = JLabel("Voce: ")
        lblYouSelectThis.setBounds(254, 45, 170, 14)
        contentPane.add(lblYouSelectThis)
        buildUserSelectionButtons(lblYouSelectThis, contentPane)

        val lblResult = JLabel("Resultado")
        lblResult.setBounds(254, 113, 135, 14)
        contentPane.add(lblResult)
        return Triple(lblPcSelectThis, lblYouSelectThis, lblResult)
    }
}
