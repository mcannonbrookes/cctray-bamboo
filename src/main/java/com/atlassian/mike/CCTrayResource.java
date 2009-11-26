package com.atlassian.mike;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.bamboo.resultsummary.BuildResultsSummaryManager;
import com.atlassian.bamboo.resultsummary.ExtendedBuildResultsSummary;
import com.atlassian.bamboo.build.BuildManager;
import com.atlassian.bamboo.build.Build;
import com.atlassian.bamboo.builder.BuildState;
import com.atlassian.bamboo.configuration.AdministrationConfigurationManager;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("/cc")
public class CCTrayResource
{
    private BuildManager buildManager;
    private AdministrationConfigurationManager config;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS0000+0:00");

	public CCTrayResource(BuildManager buildManager, AdministrationConfigurationManager config)
	{
        this.buildManager = buildManager;
        this.config = config;
    }


	@GET
	@AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Project> getProjects()
	{
        List<Project> projects = new LinkedList<Project>();

        if (buildManager != null) {
            Collection<Build> plans = buildManager.getAllBuilds();
            for (Build build : plans) {
                ExtendedBuildResultsSummary summary = build.getLatestBuildSummary();
                String status = (summary.getBuildState() == BuildState.SUCCESS ? "Success" : "Exception");
                projects.add(new Project(build.getName(), summary.getReasonSummary(), status, summary.getBuildResultKey(),
                        df.format(summary.getBuildCompletedDate()), null,
                        config.getAdministrationConfiguration().getBaseUrl() + "/browse/" + summary.getBuildResultKey()));
            }
        }

        return projects;
	}
}