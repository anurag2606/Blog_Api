package com.codewithanurag.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.description.field.FieldDescription.InGenericShape;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	
	private boolean lastPage;
}
