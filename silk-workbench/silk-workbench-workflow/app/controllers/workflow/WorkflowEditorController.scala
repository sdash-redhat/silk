package controllers.workflow

import org.silkframework.workspace.activity.workflow.{LocalWorkflowExecutor, LocalWorkflowExecutorGeneratingProvenance, Workflow}
import play.api.mvc.{Action, AnyContent, Controller}
import plugins.Context

class WorkflowEditorController extends Controller {

  def editor(project: String, task: String): Action[AnyContent] = Action { implicit request =>
    val context = Context.get[Workflow](project, task, request.path)
    Ok(views.html.workflow.editor.editor(context))
  }

  def report(project: String, task: String): Action[AnyContent] = Action { implicit request =>
    val context = Context.get[Workflow](project, task, request.path)
    val report = context.task.activity[LocalWorkflowExecutorGeneratingProvenance].value
    Ok(views.html.workflow.executionReport(report.report, context.project.config.prefixes, context))
  }
}
