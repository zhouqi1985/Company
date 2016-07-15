package readExcel;

import java.io.File;
import java.io.IOException;

import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

public class readExcel {
	private Sheet st;
	private Workbook wb;
	public  int rows;
	public int columns;
	
	public void getExcel(String path,String sheetNane) throws JXLException, IOException{
		this.wb=Workbook.getWorkbook(new File(path));
		this.st=wb.getSheet(sheetNane);
		this.rows=st.getRows();
		this.columns=st.getColumns();
	}
	
	public String readColumn(String colName,int i) throws JXLException, IOException{
			for(int j=0;j<columns;j++){
				if(colName.equals(st.getCell(j, 0).getContents())){
					return st.getCell(j,i).getContents();
				}
			}
		return null;
     }
}
