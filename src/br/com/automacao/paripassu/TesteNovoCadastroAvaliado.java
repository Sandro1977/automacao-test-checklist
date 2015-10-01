package br.com.automacao.paripassu;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteNovoCadastroAvaliado {

	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeClass
	public static void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://teste.vaga-qa.paripassudev.com");
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void QuestionarioNovoAvaliado() throws InterruptedException {

		// interrogando os elementos tela de acesso
		WebElement usuario = driver.findElement(By.id("inputUsuario"));
		WebElement senha = driver.findElement(By.id("inputPassword"));
		WebElement entrar = driver.findElement(By.xpath("//button[@class='btn btn-primary ng-binding']"));

		// clicando e preenchendo os campos tela de acesso
		Thread.sleep(500);
		usuario.sendKeys("sandrogoncalves84@yahoo.com.br");
		senha.sendKeys("cf4c97e47f");
		entrar.click();
		Thread.sleep(5000);

//		this.parteUM();
		
		Thread.sleep(5000);
		//aplicando o questionário
		//menu aplicação de questionário
		WebElement aplicacaoQuestionario = driver.findElement(By.linkText("Aplicação de questionário"));
		aplicacaoQuestionario.click();
		Thread.sleep(500);
		WebElement novaAplicacao = driver.findElement(By.linkText("Nova aplicação de questionário"));
		novaAplicacao.click();
		Thread.sleep(5000);
		//seleciona o questionário cadastrado
		WebElement novoQuestionario = driver.findElement(By.xpath("//a[@ng-click='goToEscolhaAvaliado(questionario)']"));
		novoQuestionario.click();
		Thread.sleep(5000);
		//seleciona o avaliado
		WebElement aplicacaoAvaliado = driver.findElement(By.xpath("//a[@ng-click='goToAplicacao(avaliado)']"));
		aplicacaoAvaliado.click();
		//localiza e insere topico 1
		Thread.sleep(2000);
		
		editTopicoQuestionario(1, "minha resposta 1");
		editTopicoQuestionario(2, "minha resposta 2");
		editTopicoQuestionario(3, "minha resposta 3");
		
		WebElement salvar = driver.findElement(By.linkText("Salvar"));
		salvar.click();
	}
	
	private void editTopicoQuestionario(int i, String texto) throws InterruptedException {
		List<WebElement> topicos = driver.findElements(By.cssSelector("tr[ng-click='goTo(topico.id)']"));
		
		if (topicos.size() >= i) {
			WebElement topico = topicos.get(i - 1);
			topico.click();
			Thread.sleep(500);

			WebElement questao = driver.findElement(By.xpath("//textarea[@ng-disabled='questao.disabled']"));
			questao.clear();
			questao.sendKeys(texto);
			
		}
	}

	private void parteUM() throws InterruptedException{
		// interrogando os elementos no cadastro do novo avaliado
		// Clica nos elementos no cadastro do novo avaliado
		WebElement avaliados = driver.findElement(By.linkText("Avaliados"));
		avaliados.click();
		Thread.sleep(500);
		WebElement novoAvaliado = driver.findElement(By.linkText("Novo avaliado"));
		novoAvaliado.click();

		// cadastrando novo avaliado
		Thread.sleep(3000);
		WebElement nomeAvaliado = driver.findElement(By.name("avaliadoNome"));
		nomeAvaliado.sendKeys("Sandro");
		Thread.sleep(3000);
		WebElement salvarAvaliado = driver.findElement(By.linkText("Salvar"));
		salvarAvaliado.click();

		// cadastrando novo questionário
		Thread.sleep(5000);
		WebElement questionarios = driver.findElement(By.linkText("Questionarios"));
		questionarios.click();
		Thread.sleep(500);
		WebElement novoQuestionario = driver.findElement(By.linkText("Novo questionário"));
		novoQuestionario.click();

		// selecionando e indo para a janela de tópicos
		Thread.sleep(3000);
		Select selectAvaliado = new Select(driver.findElement(By.name("questionarioMode")));
		Thread.sleep(500);
		selectAvaliado.selectByIndex(1);
		Thread.sleep(500);
		WebElement proximo = driver.findElement(By.linkText("Próximo"));
		proximo.click();

		// Adicionando e editando tópicos
		Thread.sleep(5000);
		
		editLastTopico("esta empresa?");
		
		WebElement adicionarTopico = driver.findElement(By.xpath("//button[@ng-click='addTopic()']"));
		adicionarTopico.click();
		editLastTopico("esta empresa?");

		adicionarTopico.click();
		editLastTopico("esta empresa?");
		//salvando o questionário
		WebElement SalvarQuestionario = driver.findElement(By.xpath("//button[@ng-click='save()']"));
		SalvarQuestionario.click();
	}

	private void editLastTopico(String texto) throws InterruptedException {
		List<WebElement> topicos = driver.findElements(By.cssSelector("div[ng-click='edit(topic.id)']"));
		if (topicos.size() > 0) {
			topicos.get(topicos.size() -1).click();
			Thread.sleep(3000);
			WebElement nomeTopico = driver.findElement(By.name("userTopicName"));
			Thread.sleep(500);
			nomeTopico.clear();
			nomeTopico.sendKeys(texto);
			Thread.sleep(5000);
			WebElement salvarTopico1 = driver.findElement(By.linkText("Salvar"));
			salvarTopico1.click();
			Thread.sleep(1000);
		}
	}
}
