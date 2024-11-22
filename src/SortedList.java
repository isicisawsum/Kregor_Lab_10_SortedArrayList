import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SortedList extends JFrame{
    JPanel mainPnl;
    JPanel title;
    JPanel middle;
    JPanel bottom;
    JButton add;
    JButton search;
    JButton quit;
    ArrayList<String> list = new ArrayList<>();
    JTextArea midArea;

    public SortedList() {

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTop();
        mainPnl.add(title, BorderLayout.NORTH);

        createMiddle();
        mainPnl.add(middle, BorderLayout.CENTER);

        createBottom();
        mainPnl.add(bottom, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(550, 750);
        setLocation(0, 0);
        setTitle("Sorted List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTop(){
        title = new JPanel();

        JLabel top = new JLabel("Sorted List");
        top.setFont(new Font("Impact", Font.PLAIN, 36));

        title.add(top);
    }

    private void createMiddle(){
        middle = new JPanel();
        midArea = new JTextArea(20, 30);
        //middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        middle.add(midArea);
    }

    private void createBottom(){
        bottom = new JPanel();

        bottom.setLayout(new GridLayout(1,2));
        add = new JButton("ADD");
        quit = new JButton("QUIT");
        search = new JButton("SEARCH");

        add.addActionListener((ActionEvent e) -> addItem(list));
        quit.addActionListener((ActionEvent e) -> System.exit(0));
        search.addActionListener((ActionEvent e) -> searchList());

        bottom.add(add);
        bottom.add(search);
        bottom.add(quit);
    }

    private void addItem(ArrayList<String> sortList){
        String a = JOptionPane.showInputDialog("What would you like to add to the list?");

        if(!a.isEmpty() || !a.trim().isEmpty()){
            //add and sort the list using insertion method
            sortList.add(a);
            midArea.append("Initial list: " + sortList + "\n");

            for(int i = 1; i < sortList.size(); i++){
                String next = sortList.get(i);

                int j = i - 1;
                while(j >= 0 && sortList.get(j).compareTo(next) > 0){
                    sortList.set(j + 1, sortList.get(j));
                    j--;
                }

                sortList.set(j + 1, next);

            }
            midArea.append("Sort list after sorted: " + sortList + "\n\n");
        }
        else{
            JOptionPane.showMessageDialog(this, "Nothing was added!");
        }
    }

    private void searchList(){
        String target = JOptionPane.showInputDialog("What would you like to search for?");
        int lo = 0;
        int hi = list.size() - 1;
        int mid = 0;

        do{
            mid = (lo + hi) / 2;
            if(target.equals(list.get(mid))){
                JOptionPane.showMessageDialog(this, target + " was found at index: " + mid);
                return;
            }
            else if (target.compareTo(list.get(mid)) > 0){
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
        }while(lo <= hi);
    }
}
