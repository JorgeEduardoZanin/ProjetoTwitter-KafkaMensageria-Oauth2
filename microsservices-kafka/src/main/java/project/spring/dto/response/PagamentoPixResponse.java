package project.spring.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import project.spring.entities.Pagamento;
import project.spring.entities.UsuarioPagamento;


public record PagamentoPixResponse(String id, String customer,
			String status, BigDecimal value, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dueDate, String billingType, String chavePix) {

	public static PagamentoPixResponse toPagamentoPixResponse(PixResponse response, KeyPixResponse key) {
		return new PagamentoPixResponse(response.id(), response.customer(), response.status(), response.value(), response.dueDate(),
				   response.billingType(), key.chavePix());
	}
	
	public Pagamento toEntity(String usuarioId, Long identificadorApiPrincipal) {
		
		Pagamento pagamento = new Pagamento(this.customer, this.dueDate, this.value, this.billingType, this.status);
		pagamento.setChavePix(this.chavePix);
		pagamento.setId(this.id);
		UsuarioPagamento usuario = new UsuarioPagamento();
		usuario.setUsuarioId(usuarioId);
		pagamento.setUsuario(usuario);
		pagamento.setIdApiPrincipal(identificadorApiPrincipal);
		return pagamento;
	}
	
}
