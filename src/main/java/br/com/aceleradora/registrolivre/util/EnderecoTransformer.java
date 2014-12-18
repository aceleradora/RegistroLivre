package br.com.aceleradora.registrolivre.util;

import br.com.aceleradora.registrolivre.model.Endereco;
import flexjson.transformer.AbstractTransformer;

public class EnderecoTransformer extends AbstractTransformer {
		
    public void transform(Object object) {
    	Endereco endereco = (Endereco) object;    	
        if( endereco == null) {
            getContext().write("{\"logradouro\":null}");
            return;
        } else if(endereco.getLogradouro() == null){
        	getContext().write("{\"logradouro\":null}");
            return;
        }
        getContext().write("{\"logradouro\":\"" + endereco.getLogradouro() + "\"}");
    }
}
