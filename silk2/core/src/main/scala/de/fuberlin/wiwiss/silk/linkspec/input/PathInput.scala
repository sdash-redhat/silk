package de.fuberlin.wiwiss.silk.linkspec.input

import de.fuberlin.wiwiss.silk.instance.{Instance, Path}

case class PathInput(path : Path) extends Input
{
  override def apply(instances : Traversable[Instance]) =
  {
    instances.find(_.variable == path.variable) match
    {
      case Some(instance) => instance.evaluate(path)
      case None => Traversable.empty
    }
  }
}
