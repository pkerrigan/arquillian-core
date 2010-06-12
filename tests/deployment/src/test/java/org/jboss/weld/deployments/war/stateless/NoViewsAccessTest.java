package org.jboss.weld.deployments.war.stateless;

import javax.ejb.EJB;
import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.weld.deployments.DeploymentTest;
import org.jboss.weld.deployments.beans.stateless.NoViewStatelessEJB;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class NoViewsAccessTest extends DeploymentTest
{
   @Inject
   NoViewStatelessEJB ejb;

   @EJB
   NoViewStatelessEJB ejb_;

   @Deployment
   public static JavaArchive assemble()
   {
      return createCDIArchive(NoViewStatelessEJB.class);
   }

   @Test
   public void runTest()
   {
      Assert.assertNotNull(ejb);
      Assert.assertNotNull(ejb_);
      ejb.ping();
      ejb_.ping();
      Assert.assertTrue(ejb.isPinged());
      Assert.assertTrue(ejb_.isPinged());
      //Assert.assertTrue(getNamedBean("noViewStatelessEJB", NoViewStatelessEJB.class).isPinged());
   }

}