package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.repository.KittenRepository;
import com.github.jendap.qibernate.spring.service.MeowService;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MeowTest extends SpringTestBase {
    @Inject
    MeowService meowService;
    @Inject
    CatRepository catRepository;
    @Inject
    KittenRepository kittenRepository;

//	@Test
//	public void testCatRepository() {
//		final Iterable<Cat> cats = catRepository.findAll(cat.name.like("cat0%"));
//		final Iterator<Cat> iterator = cats.iterator();
//		assertTrue(iterator.hasNext());
//		final Cat firstCat = iterator.next();
//		assertFalse(iterator.hasNext());
//		assertThat(firstCat.getName(), equalTo("cat0.name"));
//	}

    @Test
    public void testFoo() {
        try {
            meowService.foo(1L);
        } catch (final Throwable t) {
            log.error(">>>> Exception", t);
        }
    }
}
