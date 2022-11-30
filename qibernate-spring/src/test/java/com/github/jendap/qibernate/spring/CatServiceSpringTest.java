package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;
import jakarta.inject.Inject;
import org.junit.Test;

import java.util.Iterator;

import static com.github.jendap.qibernate.model.QCat.cat;
import static org.junit.Assert.*;

public class CatServiceSpringTest extends SpringTestBase {
    private static final String CAT0_NAME_PREFIX = "cat0";

    @Inject
    CatService catService;
    @Inject
    CatRepository catRepository;

    @Test
    public void testCatRepository() {
        assertEquals(CAT0_NAME_PREFIX + ".name", findCat0().getName());
    }

    @Test
    public void testCatService() {
        final int playBy = 20;
        final int feedBy = 10;
        final int originalHunger = findCat0().getHunger();
        catService.playCatsByName(findCat0().getName(), playBy);
        assertEquals(originalHunger + playBy, findCat0().getHunger());
        catService.feedAllStarvingCats(playBy, feedBy);
        assertEquals(originalHunger + playBy - feedBy, findCat0().getHunger());
    }

    private Cat findCat0() {
        final Iterable<Cat> cats = catRepository.findAll(cat.name.like(CAT0_NAME_PREFIX + "%"));
        final Iterator<Cat> iterator = cats.iterator();
        assertTrue(iterator.hasNext());
        final Cat firstCat = iterator.next();
        assertFalse(iterator.hasNext());
        return firstCat;
    }
}
