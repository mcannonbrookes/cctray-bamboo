package com.atlassian.mike;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Date;

@XmlRootElement (name="Project")
public class Project {
    /*
    <Project
        name="SvnTest"
        activity="Sleeping"
        lastBuildStatus="Exception"
        lastBuildLabel="8"
        lastBuildTime="2005-09-28T10:30:34.6362160+01:00"
        nextBuildTime="2005-10-04T14:31:52.4509248+01:00"
        webUrl="http://mrtickle/ccnet/"/>
    */

    @XmlAttribute
    String name;

    @XmlAttribute
    String activity;

    @XmlAttribute
    String lastBuildStatus;

    @XmlAttribute
    String lastBuildLabel;

    @XmlAttribute
    String lastBuildTime;

    @XmlAttribute
    String nextBuildTime;

    @XmlAttribute
    String webUrl;

    public Project() {
    }

    public Project(String name, String activity, String lastBuildStatus,
                   String lastBuildLabel, String lastBuildTime, String nextBuildTime, String webUrl) {
        this.name = name;
        this.activity = activity;
        this.lastBuildStatus = lastBuildStatus;
        this.lastBuildLabel = lastBuildLabel;
        this.lastBuildTime = lastBuildTime;
        this.nextBuildTime = nextBuildTime;
        this.webUrl = webUrl;
    }
}
