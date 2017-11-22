package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgWrapper {
	@JsonProperty("Orgs")
	private List<Organizations> Orgs;
	
	public List<Organizations> getIds(){
		return Orgs;
	}
	public void setIds(List<Organizations> ids) {
		this.Orgs = ids;
	}
}
