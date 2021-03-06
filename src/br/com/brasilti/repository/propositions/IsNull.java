/*
   Copyright 2011 Benedito Barbosa Ribeiro Neto/Christian Linhares Peixoto/Mauricio da Silva Marinho

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package br.com.brasilti.repository.propositions;

import javax.persistence.Query;

import br.com.brasilti.repository.enums.VerbEnum;

/**
 * Define que o atributo de uma instancia seja nulo. 
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * @author Mauricio Marinho
 *
 */
public class IsNull extends SimpleProposition {

	private IsNull(String fieldName, Object value) {
		super(fieldName, value);
	}

	public IsNull(String fieldName) {
		this(fieldName, null);
	}

	@Override
	public VerbEnum getVerb() {
		return VerbEnum.IS_NULL;
	}

	@Override
	public void fillQLString(StringBuilder builder) {
		builder.append("o.");
		builder.append(this.getSubject());
		builder.append(this.getVerb().getValue());
	}

	@Override
	public void setParameters(Query query) {

	}

}
