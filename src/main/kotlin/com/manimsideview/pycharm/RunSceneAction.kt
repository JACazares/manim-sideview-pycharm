package com.manimsideview.pycharm

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.ui.Messages

class RunSceneAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE)
            ?: return
        runManim(project, file)
    }

    private fun runManim(project: Project, file: VirtualFile) {
        FileDocumentManager.getInstance().saveAllDocuments()
        try {
            val process = ProcessBuilder("manim", file.path)
                .redirectErrorStream(true)
                .start()
            val output = process.inputStream.bufferedReader().readText()
            process.waitFor()
            Messages.showInfoMessage(project, output.take(1000), "Manim Output")
        } catch (ex: Exception) {
            Messages.showErrorDialog(project, ex.message ?: "Unknown error", "Manim Error")
        }
    }
}
