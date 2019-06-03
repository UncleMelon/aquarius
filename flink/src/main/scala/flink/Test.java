package flink;

public class Test {

    public static void main(String[] args) {
        String s = "{\"posts_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"pin\",\"type\":\"BOOLEAN\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"involveMembers\",\"type\":\"TEXT ARRAY\"}],\"tasks_scrum_fact\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_executorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_sprintId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_taskflowstatusId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"storyPoint\",\"type\":\"DOUBLE\"},{\"name\":\"workTime\",\"type\":\"JSONB\"},{\"name\":\"modifiedAt\",\"type\":\"TIMESTAMP\"},{\"name\":\"accomplished\",\"type\":\"TIMESTAMP\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isSubtask\",\"type\":\"BOOLEAN\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"}],\"scenariofieldconfigs_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"proTemplateConfigType\",\"type\":\"VARCHAR(200)\"}],\"crm_organizations_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"name\",\"type\":\"VARCHAR(500)\"}],\"sprints_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"name\",\"type\":\"VARCHAR(800)\"},{\"name\":\"status\",\"type\":\"VARCHAR(20)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"startDate\",\"type\":\"TIMESTAMP\"},{\"name\":\"dueDate\",\"type\":\"TIMESTAMP\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"accomplished\",\"type\":\"TIMESTAMP\"},{\"name\":\"planToDo\",\"type\":\"JSONB\"}],\"worktimes_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_taskId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_executorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"value\",\"type\":\"BIGINT\"},{\"name\":\"recordDate\",\"type\":\"TIMESTAMP\"}],\"works_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_parentId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"fileType\",\"type\":\"VARCHAR(400)\"},{\"name\":\"lastVersionTime\",\"type\":\"TIMESTAMP\"},{\"name\":\"lastUploaderId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"involveMembers\",\"type\":\"TEXT ARRAY\"}],\"events_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"startDate\",\"type\":\"TIMESTAMP\"},{\"name\":\"endDate\",\"type\":\"TIMESTAMP\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"involveMembers\",\"type\":\"TEXT ARRAY\"}],\"tasks_customfields_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_customfieldId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"customfieldName\",\"type\":\"VARCHAR(900)\"},{\"name\":\"_choiceIds\",\"type\":\"TEXT ARRAY\"},{\"name\":\"choiceValues\",\"type\":\"TEXT ARRAY\"},{\"name\":\"type\",\"type\":\"VARCHAR(100)\"}],\"projects_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"proTemplateType\",\"type\":\"VARCHAR(100)\"}],\"activities_fact\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"action\",\"type\":\"VARCHAR(100)\"},{\"name\":\"content\",\"type\":\"JSONB\"},{\"name\":\"boundToObjectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"boundToObjectType\",\"type\":\"VARCHAR(50)\"}],\"members_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_userId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_boundToObjectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"boundToObjectType\",\"type\":\"VARCHAR(200)\"},{\"name\":\"isDisabled\",\"type\":\"BOOLEAN\"},{\"name\":\"joined\",\"type\":\"TIMESTAMP\"}],\"scrumreports_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_boundToObjectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"boundToObjectType\",\"type\":\"VARCHAR(200)\"},{\"name\":\"recordType\",\"type\":\"VARCHAR(200)\"},{\"name\":\"total\",\"type\":\"BIGINT\"},{\"name\":\"plan\",\"type\":\"BIGINT\"},{\"name\":\"actual\",\"type\":\"BIGINT\"},{\"name\":\"addedStories\",\"type\":\"BIGINT\"},{\"name\":\"reducedStories\",\"type\":\"BIGINT\"},{\"name\":\"fixedBugs\",\"type\":\"BIGINT\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"}],\"tasks_dimension\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_tasklistId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_stageId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_creatorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_executorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"priority\",\"type\":\"INT\"},{\"name\":\"isDone\",\"type\":\"BOOLEAN\"},{\"name\":\"accomplished\",\"type\":\"TIMESTAMP\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"dueDate\",\"type\":\"TIMESTAMP\"},{\"name\":\"startDate\",\"type\":\"TIMESTAMP\"},{\"name\":\"involveMembers\",\"type\":\"TEXT ARRAY\"},{\"name\":\"customfields\",\"type\":\"JSONB ARRAY\"},{\"name\":\"workTime\",\"type\":\"JSONB\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isSubtask\",\"type\":\"BOOLEAN\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"storyPoint\",\"type\":\"DOUBLE\"},{\"name\":\"progress\",\"type\":\"DOUBLE\"},{\"name\":\"_scenariofieldconfigId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_sprintId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_taskflowstatusId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"workTimeTotal\",\"type\":\"DOUBLE\"},{\"name\":\"workTimeUsed\",\"type\":\"DOUBLE\"},{\"name\":\"proTemplateConfigType\",\"type\":\"VARCHAR(10)\"},{\"name\":\"tagIds\",\"type\":\"TEXT ARRAY\"}],\"member_team_fact\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_userId\",\"type\":\"VARCHAR(50)\"},{\"name\":\"isDisabled\",\"type\":\"BOOLEAN\"},{\"name\":\"joined\",\"type\":\"TIMESTAMP\"},{\"name\":\"_teamId\",\"type\":\"VARCHAR(50)\"},{\"name\":\"_organizationId\",\"type\":\"VARCHAR(50)\"},{\"name\":\"deleted\",\"type\":\"TIMESTAMP\"},{\"name\":\"role\",\"type\":\"BIGINT\"}],\"user_active_fact\":[{\"name\":\"_id\",\"type\":\"VARCHAR(50)\"},{\"name\":\"lastEnteredWeb\",\"type\":\"DATE\"},{\"name\":\"lastEnteredOther\",\"type\":\"DATE\"},{\"name\":\"lastEnteredLast\",\"type\":\"DATE\"}],\"testcase_fact\":[{\"name\":\"_id\",\"type\":\"VARCHAR(24)\"},{\"name\":\"created\",\"type\":\"TIMESTAMP\"},{\"name\":\"_executorId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_flowstatusId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_testplanId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"isDeleted\",\"type\":\"BOOLEAN\"},{\"name\":\"isArchived\",\"type\":\"BOOLEAN\"},{\"name\":\"isDone\",\"type\":\"BOOLEAN\"},{\"name\":\"accomplished\",\"type\":\"TIMESTAMP\"}],\"task_status_update_fact\":[{\"name\":\"_id\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_projectId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_stageId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"_taskflowstatusId\",\"type\":\"VARCHAR(24)\"},{\"name\":\"isDone\",\"type\":\"BOOLEAN\"},{\"name\":\"statusUpdated\",\"type\":\"TIMESTAMP\"}]}";
        System.out.println(s);

    }
}