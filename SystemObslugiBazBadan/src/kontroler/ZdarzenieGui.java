package kontroler;

import model.Badanie;
import model.Model;
import model.Pacjent;
import widok.WidokGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Klasa reprezentująca zdarzenia występujące w aplikacji
 */
public class ZdarzenieGui implements ActionListener {

    private WidokGui widok = null;
    private Model model = null;

    /**
     * Konstruktor
     * @param w
     * @param m
     */
    public ZdarzenieGui(WidokGui w, Model m){
        this.widok = w;
        this.model = m;
    }

    /**
     * metoda odpowiedzialna za wystepowanie zdarzeń
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("przegladajBadania")){
            this.widok.oknoPrzegladaj();
            this.widok.setController(this);
            System.out.println("Wybrano opcję - Przeglądaj");
        }

        else if (e.getActionCommand().equals("dodajBadanie")){
            this.widok.oknoDodaj();
            this.widok.setController(this);
            System.out.println("Wybrano opcję - Dodaj badanie");
        }

        else if (e.getActionCommand().equals("sprawdzWyniki")){
            System.out.println("Szukam pacjenta...");
            this.widok.setWyniki(this.model.findBadanie(this.widok.getSprawdzPESEL()));
            this.widok.przegladajFrame.dispatchEvent(new WindowEvent(this.widok.przegladajFrame, WindowEvent.WINDOW_CLOSING));
            this.widok.oknoPrzegladaj();
            this.widok.setController(this);
        }

        else if (e.getActionCommand().equals("generujPDF")){
            System.out.println("Generuje PDF");
            this.widok.zapiszPDF(this.widok.podgladWynikow);
        }

        else if (e.getActionCommand().equals("addRekord")) {

            Pacjent p = this.widok.getPacjent();
            Badanie b = this.widok.getBadanie();

            if(!model.checkPacjent(p.getPesel())){
                this.model.addPacjent(p);
                this.model.addBadanie(b);
                System.out.println("Pacjent został utworzony");
            } else {
                this.model.addBadanie(b);
            }
            System.out.println("Badanie zostało utworzone");
            JOptionPane.showMessageDialog(null, "Dodano!");
            this.widok.clearGui();

        }

        else if (e.getActionCommand().equals("checkPacjent")){
            this.widok.setPacjent(this.model.findPacjent(this.widok.getPESEL()));
        }

        else {
            System.out.println("Mamy problem!");
        }
    }
}
