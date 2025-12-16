package app;

import formularios.FrmRestaurante;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        /* Define o Look and Feel (Nimbus) */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Erro ao carregar o tema visual do sistema.\n"
              + "O sistema será iniciado com o tema padrão.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }

        /* Inicia a aplicação pelo formulário principal */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmRestaurante().setVisible(true);
        });
    }
}
