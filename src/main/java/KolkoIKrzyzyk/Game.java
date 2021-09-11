package KolkoIKrzyzyk;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PageTitle("Gra w Kolko i Krzyzyk")
@PWA(iconPath = "img/logo.png", name = "Gra", shortName = "Gra")
public class Game extends VerticalLayout {
    private Button b0 = new Button(""),
            b1 = new Button(""),
            b2 = new Button(""),
            b3 = new Button(""),
            b4 = new Button(""),
            b5 = new Button(""),
            b6 = new Button(""),
            b7 = new Button(""),
            b8 = new Button(""),
            bReset;
    private Button[] b;
    private H1 napis;
    private boolean xo = false;
    public Game(){
        HorizontalLayout l1 = new HorizontalLayout();
        HorizontalLayout l2 = new HorizontalLayout();
        HorizontalLayout l3 = new HorizontalLayout();
        b = new Button[]{
          b0,b1,b2,b3,b4,b5,b6,b7,b8
        };
        napis = new H1("Wygrał: ");
        add(napis);
        for (int i = 0; i < b.length; i++) {
            b[i].setHeight("100px");
            b[i].setWidth("100px");
            if(i < 3){
                l1.add(b[i]);
            }else if(i>5){
                l3.add(b[i]);
            }else {
                l2.add(b[i]);
            }
        }
        for (Button btn : b) {
            btn.addClickListener(clickEvent -> {
               btn.setEnabled(false);
               btn.setText(xo ? "X":"O");
               xo = !xo;
               sprawdz(0,1,2);
               sprawdz(3,4,5);
               sprawdz(6,7,8);

               sprawdz(0,3,6);
               sprawdz(1,4,7);
               sprawdz(2,5,8);

               sprawdz(0,4,8);
               sprawdz(2,4,6);
            });
        }

        add(l1,l2,l3);
        bReset = new Button("Reset");
        bReset.setHeight("100px");
        bReset.setWidth("330px");
        bReset.addClickListener(clickEvent -> {
            for (Button btn : b) {
                btn.setText("");
                btn.setEnabled(true);
                napis.setText("Wygrał: ");
            }
        });
        add(bReset);
    }

    private void sprawdz(int b1, int b2, int b3) {
        if(b[b1].getText().equals(b[b2].getText()) && b[b2].getText().equals(b[b3].getText()) && !b[b1].getText().equals("")){
            napis.setText(napis.getText() + (xo ? "O":"X"));
            for (Button btn : b) {
                btn.setEnabled(false);
            }
        }
    }
}
