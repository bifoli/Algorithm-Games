import java.awt.*;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // 设置自己的数据
    private SelectionSortData data;
    public void render(SelectionSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制：对已经排序的和未排序的还有未排序数组中的最小值进行不同颜色的标记
            int w = canvasWidth / data.N();
            //AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
            
            for(int i = 0; i < data.N(); i++) {	//绘制矩形
            	
            	if(i < data.orderedIndex) {
            		AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
            	}else {
            		AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
            	}
            	
            	if(i == data.currentCompareIndex) {
            		AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
            	}
            	if(i == data.currentMinIndex) {
            		AlgoVisHelper.setColor(g2d, AlgoVisHelper.Black);
            	}
            	
            	AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


