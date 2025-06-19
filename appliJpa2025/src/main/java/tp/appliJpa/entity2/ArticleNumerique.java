package tp.appliJpa.entity2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="article_numerique") //table complémentaire vis à vis de table article
public class ArticleNumerique extends Article {
	
	@Column(name="download_url")
	private String downloadUrl; //for downloading (ex: mp3 ou autre)
	
	@Column(name="description_url")
	private String descriptionUrl;
	
	public ArticleNumerique(Long id, String label, Double prix, String downloadUrl, String descriptionUrl) {
		super(id, label, prix);
		this.downloadUrl = downloadUrl;
		this.descriptionUrl = descriptionUrl;
	}

	public ArticleNumerique() {
		super();
	}

	public ArticleNumerique(Long id, String label, Double prix) {
		super(id, label, prix);
	}

	@Override
	public String toString() {
		return "ArticleNumerique [downloadUrl=" + downloadUrl + ", descriptionUrl=" + descriptionUrl + ", toString()="
				+ super.toString() + "]";
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getDescriptionUrl() {
		return descriptionUrl;
	}
	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	} 
	
	
	
}
