package com.evanco.EvanWilsonU1Capstone;

import com.evanco.EvanWilsonU1Capstone.dao.ConsoleDaoJdbcTemplateImplTest;
import com.evanco.EvanWilsonU1Capstone.dao.GameDaoJdbcTemplateImplTest;
import com.evanco.EvanWilsonU1Capstone.dao.InvoiceDaoJdbcTemplateImplTest;
import com.evanco.EvanWilsonU1Capstone.dao.TshirtDaoJdbcTemplateImplTest;
import com.evanco.EvanWilsonU1Capstone.service.ServiceLayerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConsoleDaoJdbcTemplateImplTest.class,
        GameDaoJdbcTemplateImplTest.class,
        InvoiceDaoJdbcTemplateImplTest.class,
        TshirtDaoJdbcTemplateImplTest.class,
        ServiceLayerTest.class
})
public class EvanWilsonU1CapstoneTestSuite {
}
