
public enum TiposPoda {
	Eficiente,Efectiva;
	
	public String toString(){
		if(this==Efectiva)
			return "Poda efectiva";
		return "Poda eficiente";
	}

}
