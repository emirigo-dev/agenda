package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import dto.PersonaDTO;

public class ReporteAgenda
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);
	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<PersonaDTO> personas, Set <String> preferenciaContactoNames)
    {
    
    	HashMap <String, Integer> prefenciaContactoCount = new HashMap <String,Integer>();
    	
    	for (String preferenciaContactoName : preferenciaContactoNames) {
    		prefenciaContactoCount.put(preferenciaContactoName, 0);
    	}
    	
    	for (PersonaDTO persona : personas) {
    		String preferenciaContactoName = persona.getPreferenciaContacto();
    		prefenciaContactoCount.put(preferenciaContactoName, prefenciaContactoCount.get(preferenciaContactoName) + 1);
    	}
    	
    	ArrayList <ReportePreferenciaWrapper> wrapper = new ArrayList <ReportePreferenciaWrapper>();
    	for (String key : prefenciaContactoCount.keySet()) {
    		ReportePreferenciaWrapper wr = new ReportePreferenciaWrapper(key, prefenciaContactoCount.get(key));
    		System.out.println(wr.getPreferenciaContacto() + "-" + wr.getquantity());
    		wrapper.add(wr);
    	}
    	
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	try		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes" + File.separator + "ReportePreferencia.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(wrapper));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	